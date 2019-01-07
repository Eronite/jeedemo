package com.eron.jeedemo.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eron.jeedemo.beans.Client;
import com.eron.jeedemo.beans.Order;

public final class OrderCreationForm {
    private static final String DATE_FIELD            = "orderDate";

    private String              result;
    private Map<String, String> errors                = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    public Order createOrder( HttpServletRequest request ) {
        /*
         * pour ne pas dupliquer l'objet Client : passer la requête courante à l'objet métier existant
         * et récupérer l'objet Client créé
         */
        ClientCreationForm clientForm = new ClientCreationForm();
        Client client = clientForm.createClient( request );

        /*
         * récupérer le contenu de la map d'erreurs créée par l'objet métier ClientCreationForm
         * dans la map d'erreurs courante (actuellement vide)
         */
        errors = clientForm.getErrors();

         
        String date = getFieldValue( request, DATE_FIELD );


        Order order = new Order();

        order.setClient( client );
        //order.setDate( date );

        try {
            dateValidation( date );
        } catch ( Exception e ) {
            setError( DATE_FIELD, e.getMessage() );
        }
        order.setDate( date );
        
        if ( errors.isEmpty() ) {
            result = "Succès de la création de la commande.";
        } else {
            result = "Échec de la création de la commande.";
        }
        return order;
    }

    private void dateValidation( String date ) throws Exception {
        if ( date == null ) {
            throw new Exception( "La date doit être renseignée." );
        }
    }

    /*
     * add a message to errors map (with matching field)
     */
    private void setError( String field, String message ) {
        errors.put( field, message );
    }

    /*
     * méthode utilitaire qui retourne null si un champ est vide (sinon son contenu)
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