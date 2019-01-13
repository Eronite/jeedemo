package com.eron.jeedemo.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eron.jeedemo.beans.Client;

public final class ClientCreationForm {
    private static final String NAME_FIELD       = "clientName";
    private static final String FIRSTNAME_FIELD  = "clientFirstName";

    private String              result;
    private Map<String, String> errors           = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    public Client createClient( HttpServletRequest request ) {
        String name = getFieldValue( request, NAME_FIELD );
        String firstName = getFieldValue( request, FIRSTNAME_FIELD );

        Client client = new Client();

        try {
            nameValidation( name );
        } catch ( Exception e ) {
            setError( NAME_FIELD, e.getMessage() );
        }
        client.setName( name );

        try {
            firstNameValidation( firstName );
        } catch ( Exception e ) {
            setError( FIRSTNAME_FIELD, e.getMessage() );
        }
        client.setFirstName( firstName );
        

        if ( errors.isEmpty() ) {
            result = "Succ�s de la cr�ation du client.";
        } else {
            result = "�chec de la cr�ation du client.";
        }

        return client;
    }

    private void nameValidation( String name ) throws Exception {
        if ( name != null ) {
            if ( name.length() < 2 ) {
                throw new Exception( "Le nom d'utilisateur doit contenir au moins 2 caract�res." );
            }
        } else {
            throw new Exception( "Merci d'entrer un nom d'utilisateur." );
        }
    }

    private void firstNameValidation( String firstName ) throws Exception {
        if ( firstName != null ) {
            if ( firstName.length() < 2 ) {
                throw new Exception( "Le pr�nom d'utilisateur doit contenir au moins 2 caract�res." );
            }
        } else {
            throw new Exception( "Merci d'entrer un pr�nom d'utilisateur." );
        }
    }

    /*
     * add a message to errors map (with matching field)
     */
    private void setError( String field, String message ) {
        errors.put( field, message );
    }

    /*
     * m�thode utilitaire qui retourne null si un champ est vide (sinon son contenu)
     */
    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
}