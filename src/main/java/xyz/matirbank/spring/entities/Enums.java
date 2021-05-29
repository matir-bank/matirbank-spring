/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.matirbank.spring.entities;

/**
 *
 * @author User
 */
public class Enums {
    
    enum AccountType {
        WALLET, AGENT, MERCHANT, SYSTEM;
    }
    
    enum IdentityType {
        NATIONAL_ID, DRIVING_LICENSE, PASSPORT, BIRTH_CERTIFICATE
    }
}
