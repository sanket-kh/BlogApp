package com.sanket.blogapp.util;


import org.springframework.http.HttpStatus;


public class Util {


    public static ResponseObject resourceCreated(Object object, String resourceName) {

        ResponseObject responseObject = new ResponseObject();
        responseObject.setBody(object);
        responseObject.setHttpStatus(HttpStatus.CREATED);
        responseObject.setMessage(resourceName + " created successfully");
        return responseObject;

    }


    public static ResponseObject resourceNotCreated(Object object, String resourceName) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setBody(object);
        responseObject.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseObject.setMessage(resourceName + " not created");
        return responseObject;
    }

    public static ResponseObject resourceNotFound(String resourceName) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setHttpStatus(HttpStatus.NOT_FOUND);
        responseObject.setMessage(resourceName + " not found");
        return responseObject;

    }

    public static ResponseObject resourceFound(Object object, String resourceName) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setBody(object);
        responseObject.setHttpStatus(HttpStatus.OK);
        responseObject.setMessage(resourceName + " found");
        return responseObject;

    }

    public static ResponseObject resourceUpdated(Object object, String resourceName, Long id) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setBody(object);
        responseObject.setHttpStatus(HttpStatus.OK);
        responseObject.setMessage(resourceName + " with ID:" + id + " updated successfully");
        return responseObject;

    }

    public static ResponseObject resourceNotUpdated(Object object, String resourceName, Long id) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setBody(object);
        responseObject.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseObject.setMessage(resourceName + " with ID:" + id + " not updated");
        return responseObject;

    }

    public static ResponseObject resourceDeleted(String resourceName, Long id) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMessage(resourceName + " with ID:" + id + " deleted");
        responseObject.setHttpStatus(HttpStatus.OK);
        return responseObject;
    }

    public static ResponseObject resourceNotDeleted(String resourceName) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMessage(resourceName + " not found with given id");
        responseObject.setHttpStatus(HttpStatus.OK);
        return responseObject;
    }


}
