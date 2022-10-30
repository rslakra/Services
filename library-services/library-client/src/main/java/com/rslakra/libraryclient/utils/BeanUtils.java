package com.rslakra.libraryclient.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Rohtash Lakra
 * @created 8/21/21 9:19 PM
 */
public enum BeanUtils {

    INSTANCE;

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);
    public static final String REQUEST_TRACER = "requestTracer";
    public static final String CLASS = "class";
    public static final String GET = "get";
    public static final String SET = "set";
    private final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new HashMap(8);
    private final Set<String> IMMUTABLE_ATTRIBUTES = new HashSet(Arrays.asList("id"));
    private final Map<Class<?>, Map<String, PropertyDescriptor>>
        CLASS_PROPERTY_DESCRIPTOR = new ConcurrentHashMap();
    private final ConcurrentMap<Class<?>, ClassProperties> CACHE;

    private BeanUtils() {
        primitiveWrapperTypeMap.put(Boolean.class, Boolean.TYPE);
        primitiveWrapperTypeMap.put(Byte.class, Byte.TYPE);
        primitiveWrapperTypeMap.put(Character.class, Character.TYPE);
        primitiveWrapperTypeMap.put(Double.class, Double.TYPE);
        primitiveWrapperTypeMap.put(Float.class, Float.TYPE);
        primitiveWrapperTypeMap.put(Integer.class, Integer.TYPE);
        primitiveWrapperTypeMap.put(Long.class, Long.TYPE);
        primitiveWrapperTypeMap.put(Short.class, Short.TYPE);
        CACHE = new ConcurrentHashMap(256);
    }

    /**
     * @param object
     * @return
     */
    public static boolean isNull(final Object object) {
        return Objects.isNull(object);
    }

    /**
     * @param object
     * @return
     */
    public static boolean isNotNull(final Object object) {
        return !isNull(object);
    }

    /**
     * @param object
     * @return
     */
    public static int getLength(final Object object) {
        return ((isNotNull(object) && Array.class.isAssignableFrom(object.getClass())) ? ((Object[]) object).length
                                                                                       : 0);
    }

    /**
     * Returns true if the <code>object</code> is null or empty otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isNullOrEmpty(final Object object) {
        LOGGER.debug("isNullOrEmpty({})", object);
        if (isNull(object)) {
            return true;
        } else if (object instanceof CharSequence && ((CharSequence) object).length() == 0) {
            return true;
        } else if (getLength(object) == 0) {
            return true;
        } else if (object instanceof Collection && ((Collection) object).size() == 0) {
            return true;
        } else if (object instanceof Map && ((Map) object).size() == 0) {
            return true;
        }

        return false;
    }


    /**
     * @param name
     * @return
     */
    public final boolean isModifiable(final String name) {
        return !IMMUTABLE_ATTRIBUTES.contains(name);
    }

    /**
     * @param pathSegments
     * @return
     */
    public static String pathSegments(final String... pathSegments) {
        Objects.nonNull(pathSegments);
        return String.join(File.separator, pathSegments);
    }

    /**
     * Returns the <code>PropertyDescriptor[]</code> for the given class.
     *
     * @param classType
     * @return
     * @throws IntrospectionException
     */
    public final PropertyDescriptor[] getBeanInfo(final Class<?> classType) throws IntrospectionException {
        return Introspector.getBeanInfo(classType).getPropertyDescriptors();
    }

    /**
     * @param classType
     * @param propertyDescriptor
     * @throws IntrospectionException
     */
    private final void findWriteMethod(final Class<?> classType, final PropertyDescriptor propertyDescriptor)
        throws IntrospectionException {
        if (!isClassPropertyDescriptor(propertyDescriptor) && propertyDescriptor.getReadMethod() != null) {
            final String setterMethod = getSetterMethod(propertyDescriptor);
            final Class<?> propType = getReturnType(propertyDescriptor);
            for (Method method : classType.getMethods()) {
                if (setterMethod.equals(method.getName()) && method.getParameterTypes().length == 1 && method
                    .getParameterTypes()[0].isAssignableFrom(propType)) {
                    propertyDescriptor.setWriteMethod(method);
                    return;
                }
            }
        }
    }


    /**
     * @param classType
     * @return
     */
    public final Map<String, PropertyDescriptor> findByClass(final Class<?> classType) {
        try {
            Map<String, PropertyDescriptor> typePropertyDescriptor = CLASS_PROPERTY_DESCRIPTOR.get(classType);
            if (typePropertyDescriptor == null) {
                typePropertyDescriptor = new LinkedHashMap();
                final PropertyDescriptor[] propertyDescriptors = getBeanInfo(classType);
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getWriteMethod() == null) {
                        findWriteMethod(classType, propertyDescriptor);
                    }

                    typePropertyDescriptor.put(propertyDescriptor.getName(), propertyDescriptor);
                }

                CLASS_PROPERTY_DESCRIPTOR.putIfAbsent(classType, typePropertyDescriptor);
                typePropertyDescriptor = CLASS_PROPERTY_DESCRIPTOR.get(classType);
            }

            return typePropertyDescriptor;
        } catch (IntrospectionException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Returns true if the <code>PropertyDescriptor</code> name is class otherwise false.
     *
     * @param propertyDescriptor
     * @return
     */
    public final boolean isClassPropertyDescriptor(final PropertyDescriptor propertyDescriptor) {
        return (propertyDescriptor != null && CLASS.equals(propertyDescriptor.getName()));
    }

    /**
     * @param propertyDescriptor
     * @param methodType
     * @return
     */
    public final String toMutatorMethod(final PropertyDescriptor propertyDescriptor, final String methodType) {
        return String.format("%s%s%s", methodType, propertyDescriptor.getName().substring(0, 1).toUpperCase(),
                             propertyDescriptor.getName().substring(1));
    }

    /**
     * @param propertyDescriptor
     * @return
     */
    public final String getSetterMethod(final PropertyDescriptor propertyDescriptor) {
        return toMutatorMethod(propertyDescriptor, SET);
    }

    /**
     * @param propertyDescriptor
     * @return
     */
    public final String getGetterMethod(final PropertyDescriptor propertyDescriptor) {
        return toMutatorMethod(propertyDescriptor, GET);
    }

    /**
     * Returns the type of the property.
     *
     * @param propertyDescriptor
     * @return
     */
    public final Class<?> getReturnType(final PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getReadMethod().getReturnType();
    }

    /**
     * Ensures that the method is public and accessible.
     *
     * @param method
     */
    public final void ensurePublic(final Method method) {
        if (method != null && !Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            method.setAccessible(true);
        }
    }


    /**
     * @param classType
     * @param name
     * @return
     */
    public final PropertyDescriptor findByName(final Class<?> classType, final String name) {
        return findByClass(classType).get(name);
    }

    /**
     * Returns the properties for the provided class.
     *
     * @param classType
     * @return
     */
    public final Collection<PropertyDescriptor> getProperties(final Class<?> classType) {
        return findByClass(classType).values();
    }

    /**
     * @param propertyDescriptor
     * @param bean
     * @return
     */
    public final Object get(final PropertyDescriptor propertyDescriptor, final Object bean) {
        try {
            return propertyDescriptor.getReadMethod().invoke(bean);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex.getTargetException());
        }
    }

    /**
     * @param propertyDescriptor
     * @param bean
     * @param value
     */
    public final void set(PropertyDescriptor propertyDescriptor, Object bean, Object value) {
        try {
            propertyDescriptor.getWriteMethod().invoke(bean, value);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex.getTargetException());
        }
    }


    /**
     * @param classType
     * @return
     */
    public boolean isSimpleProperty(final Class<?> classType) {
        Objects.requireNonNull(classType, "Class must not be null!");
        return isSimpleValueType(classType) || classType.isArray() && isSimpleValueType(classType.getComponentType());
    }

    /**
     * @param classType
     * @return
     */
    public boolean isSimpleValueType(final Class<?> classType) {
        return isPrimitiveOrWrapper(classType) || classType.isEnum() || CharSequence.class
            .isAssignableFrom(classType) || Number.class.isAssignableFrom(classType) || Date.class
                   .isAssignableFrom(classType) || classType.equals(URI.class) || classType.equals(URL.class)
               || classType.equals(Locale.class) || classType.equals(Class.class);
    }

    /**
     * @param classType
     * @return
     */
    public boolean isPrimitiveWrapper(final Class<?> classType) {
        Objects.requireNonNull(classType, "Class must not be null!");
        return primitiveWrapperTypeMap.containsKey(classType);
    }

    /**
     * @param classType
     * @return
     */
    public boolean isPrimitiveOrWrapper(final Class<?> classType) {
        Objects.requireNonNull(classType, "Class must not be null!");
        return classType.isPrimitive() || isPrimitiveWrapper(classType);
    }

    /**
     * @param classType
     * @return
     */
    private ClassProperties getPropertyDescriptors(final Class<?> classType) {
        ClassProperties classProperties = CACHE.get(classType);
        if (classProperties == null) {
            CACHE.putIfAbsent(classType, new ClassProperties(classType));
            classProperties = (ClassProperties) CACHE.get(classType);
        }

        return classProperties;
    }

    /**
     * @param source
     * @param target
     * @param ignoredProperties
     * @throws IllegalStateException
     */
    public void copyProperties(final Object source, final Object target, final String... ignoredProperties)
        throws IllegalStateException {
        Objects.requireNonNull(source, "Source must not be null!");
        Objects.requireNonNull(target, "Target must not be null!");
        Object name = null;
        try {
            final ClassProperties sourceProperties = getPropertyDescriptors(source.getClass());
            final ClassProperties targetProperties = getPropertyDescriptors(target.getClass());
            final Set<String> ignoreLookup = Sets.asSet(ignoredProperties);
            final CachedPropertyDescriptor[] writeProperties = targetProperties.writeProperties;
            for (int i = 0; i < writeProperties.length; ++i) {
                CachedPropertyDescriptor targetProperty = writeProperties[i];
                name = targetProperty.name;
                if (!ignoreLookup.contains(targetProperty.name)) {
                    CachedPropertyDescriptor sourceProperty = sourceProperties.readProperties.get(targetProperty.name);
                    if (sourceProperty != null) {
                        final Object value = sourceProperty.readMethod.invoke(source);
                        try {
                            targetProperty.writeMethod.invoke(target, value);
                        } catch (IllegalArgumentException ex) {
                            //ignore me
                        }
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            LOGGER.error("com.rslakra.algos.exception copy property %s:%s",
                         new Object[]{target.getClass().getName(), name});
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @param source
     * @param target
     * @param ignoredProperties
     * @throws IllegalStateException
     */
    public void deepCopyProperties(final Object source, final Object target, final String... ignoredProperties)
        throws IllegalStateException {
        Objects.requireNonNull(source, "Source must not be null!");
        Objects.requireNonNull(target, "Target must not be null!");
        Object name = null;
        try {
            final ClassProperties sourceProperties = getPropertyDescriptors(source.getClass());
            final ClassProperties targetProperties = getPropertyDescriptors(target.getClass());
            final Set<String> ignoreLookup = Sets.asSet(ignoredProperties);
            final CachedPropertyDescriptor[] writeProperties = targetProperties.writeProperties;
            for (int i = 0; i < writeProperties.length; ++i) {
                CachedPropertyDescriptor targetProperty = writeProperties[i];
                name = targetProperty.name;
                if (!ignoreLookup.contains(targetProperty.name)) {
                    CachedPropertyDescriptor sourceProperty = sourceProperties.readProperties.get(targetProperty.name);
                    if (sourceProperty != null) {
                        if (isSimpleProperty(sourceProperty.classType)) {
                            final Object value = sourceProperty.readMethod.invoke(source);
                            try {
                                targetProperty.writeMethod.invoke(target, value);
                            } catch (IllegalArgumentException ex) {
                                //ignore me
                            }
                        } else {
                            try {
                                final Object sourceObject = sourceProperty.readMethod.invoke(source);
                                final Object targetObject = targetProperty.classType.newInstance();
                                deepCopyProperties(sourceObject, targetObject);
                                try {
                                    targetProperty.writeMethod.invoke(target, targetObject);
                                } catch (IllegalArgumentException ex) {
                                    //ignore me
                                }
                            } catch (InstantiationException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            LOGGER.error("com.rslakra.algos.exception copy property %s:%s",
                         new Object[]{target.getClass().getName(), name});
            throw new IllegalStateException(ex);
        }
    }


    /**
     * CachedPropertyDescriptor
     */
    private final class CachedPropertyDescriptor {

        public final String name;
        public final Class<?> classType;
        public final Method readMethod;
        public final Method writeMethod;

        /**
         * @param propertyDescriptor
         */
        public CachedPropertyDescriptor(final PropertyDescriptor propertyDescriptor) {
            this.name = propertyDescriptor.getName();
            classType = propertyDescriptor.getReadMethod().getReturnType();
            this.readMethod = propertyDescriptor.getReadMethod();
            this.writeMethod = propertyDescriptor.getWriteMethod();
        }

        /**
         * @return
         * @throws IllegalAccessException
         * @throws InstantiationException
         */
        public Object newInstance() throws IllegalAccessException, InstantiationException {
            if (classType.equals(Set.class)) {
                return new HashSet<>();
            } else if (classType.equals(Map.class)) {
                return new HashMap<>();
            } else if (classType.equals(List.class)) {
                return new ArrayList<>();
            } else {
                return classType.newInstance();
            }
        }
    }

    /**
     * Contains the class properties.
     */
    private final class ClassProperties {

        public final Map<String, CachedPropertyDescriptor> readProperties;
        public final CachedPropertyDescriptor[] writeProperties;

        /**
         * @param classType
         */
        public ClassProperties(final Class<?> classType) {
            final Collection<PropertyDescriptor> allProperties = getProperties(classType);
            this.readProperties = new HashMap(allProperties.size(), 1.0F);
            final List<CachedPropertyDescriptor> writeList = new ArrayList(allProperties.size());
            allProperties.forEach(propertyDescriptor -> {
                ensurePublic(propertyDescriptor.getReadMethod());
                ensurePublic(propertyDescriptor.getWriteMethod());
                if (propertyDescriptor.getReadMethod() != null) {
                    this.readProperties
                        .put(propertyDescriptor.getName(), new CachedPropertyDescriptor(propertyDescriptor));
                }

                if (propertyDescriptor.getWriteMethod() != null) {
                    writeList.add(new CachedPropertyDescriptor(propertyDescriptor));
                }
            });

            this.writeProperties = writeList.toArray(new CachedPropertyDescriptor[writeList.size()]);
        }
    }

    /**
     * Returns the unique random <code>UUID</code> string.
     *
     * @return
     */
    public static String nextUuid() {
        return UUID.randomUUID().toString();
    }

}
