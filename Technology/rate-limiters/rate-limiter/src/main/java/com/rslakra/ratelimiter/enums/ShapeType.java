package com.rslakra.ratelimiter.enums;

/**
 * Reference
 *
 * <pre>
 *  https://en.wikipedia.org/wiki/Rectangle
 *  https://byjus.com/maths/geometric-shapes/
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 4/14/23 3:10 PM
 */
public enum ShapeType {
    ARC,
    /**
     * Locus of all points at a fixed distance from a reference central point is called a Circle.
     */
    CIRCLE,
    DIAMOND,
    ELLIPSE,
    HEPTAGON,
    HEXAGON,

    /**
     * These are made up of line segments and no curves. They are enclosed structures based on different lengths of
     * sides and different angles. Polygons (Pentagon, Hexagon, Octagon, Nonagon, Decagon, etc.)
     */
    POLYGON,
    OCTAGON,

    /**
     * A quadrilateral has two pairs of opposite sides equal in length and interior angles are at the right angles.
     */
    RECTANGLE,
    ROUND_RECTANGLE,

    /**
     * Square is a quadrilateral where all the four sides and angles are equal and the angles at all the vertices are
     * equal to 90° each.
     */
    SQUARE,
    TRAPEZOID,

    /**
     * Triangle is a polygon, which is made of three sides and consists of three edges and three vertices. Also, the sum
     * of its internal angles equals to 180o.
     */
    TRIANGLE,
    UNSUPPORTED,
    WAVE,
    ;
}
