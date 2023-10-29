package com.rslakra.healthcare.routinecheckup.utils.constants;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:48 PM
 */
public interface ViewNames {

    String LOGIN_VIEW_NAME = "login";

    String LOGIN_URL = "/" + LOGIN_VIEW_NAME;

    String LOGOUT_URL = "/logout";

    String DOCTORS_AND_PATIENTS_LIST_VIEW_NAME = "doctors_and_patients_list";

    String DOCTORS_AND_PATIENTS_LIST_URL
        = "/" + DOCTORS_AND_PATIENTS_LIST_VIEW_NAME;

    String UPDATE_PATIENT_VIEW_NAME = "update_patient";

    String UPDATE_PATIENT_URL = "/" + UPDATE_PATIENT_VIEW_NAME;

    String UPDATE_DOCTOR_VIEW_NAME = "update_doctor";

    String UPDATE_DOCTOR_URL = "/" + UPDATE_DOCTOR_VIEW_NAME;

    String UPDATE_USER_VIEW_NAME = "update_user";

    String UPDATE_USER_URL = "/" + UPDATE_USER_VIEW_NAME;

    String REGISTRATION_VIEW_NAME = "registration";

    String REGISTRATION_URL = "/" + REGISTRATION_VIEW_NAME;

    String DOCTOR_PAYMENT_URL = "/doc_payment";

    String DOCTOR_PAYMENT_URL_TEMPLATE = DOCTOR_PAYMENT_URL + "/{doc_id}";

    String ADMIN_VIEW_NAME = "admin";

    String ADMIN_BASE_PATH = "/" + ADMIN_VIEW_NAME;

    String PRE_COMPLETE_REGISTRATION_VIEW_NAME = "pre_complete_registration";

    String MONTHLY_REPORTS_VIEW_NAME = "monthly_report";

    String MONTHLY_REPORTS_URL = "/" + MONTHLY_REPORTS_VIEW_NAME;

    String RENAME_MONTHLY_REPORT_VIEW_NAME = "rename_report";

    String RENAME_MONTHLY_REPORT_URL = "/" + RENAME_MONTHLY_REPORT_VIEW_NAME;

    String CHANGE_USER_PICTURE_VIEW_NAME = "user_pic";

    String CHANGE_USER_PICTURE_URL = "/" + CHANGE_USER_PICTURE_VIEW_NAME;

    String SERVICES_VIEW_NAME = "services";

    String SERVICES_URL = "/" + SERVICES_VIEW_NAME;

    String SEARCH_DOCTOR_VIEW_NAME = "doctors";

    String SEARCH_DOCTOR_URL = "/" + SEARCH_DOCTOR_VIEW_NAME;

}

