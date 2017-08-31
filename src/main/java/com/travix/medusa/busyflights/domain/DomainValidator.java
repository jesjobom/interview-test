/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travix.medusa.busyflights.domain;

/**
 *
 * @author jesjobom
 */
public abstract class DomainValidator {
    
    protected String validationMessage;
    
    public abstract boolean isValid();

    public String getValidationMessage() {
        return validationMessage;
    }

    protected void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}
