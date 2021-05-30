/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.matirbank.spring.models;

/**
 *
 * @author User
 */
public class Enums {
    
    public enum AccountType {
        WALLET, AGENT, MERCHANT, SYSTEM;
    }
    
    public enum IdentityType {
        NATIONAL_ID, DRIVING_LICENSE, PASSPORT, BIRTH_CERTIFICATE
    }
}
