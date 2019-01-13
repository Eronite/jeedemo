package com.eron.jeedemo.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eron.jeedemo.beans.Client;
import com.eron.jeedemo.beans.Order;

public final class OrderCreationForm {
    private static final String DATE_FIELD            = "orderDate";
    
    
    private static final String CHOICE_CLIENT_FIELD   = "choiceNewClient";
    private static final String LIST_CLIENTS_FIELD    = "listClients";

    private static final String EXISTING_CLIENT       = "existingClient";
    private static final String CLIENTS_SESSION       = "clients";
    
    
    private String              result;
    private Map<String, String> errors                = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    public Order createOrder( HttpServletRequest request ) {
    	Client client;
        /*
         * Si l'utilisateur choisit un client d�j� existant, pas de validation �
         * effectuer
         */
        String choiceNewClient = getFieldValue( request, CHOICE_CLIENT_FIELD );
        if ( EXISTING_CLIENT.equals( choiceNewClient ) ) {
            /* R�cup�ration du nom du client choisi */
            String existingClientName = getFieldValue( request, LIST_CLIENTS_FIELD );
            /* R�cup�ration de l'objet client correspondant dans la session */
            HttpSession session = request.getSession();
            client = ( (Map<String, Client>) session.getAttribute( CLIENTS_SESSION ) ).get( existingClientName );
        } else {
            /*
             * Sinon on garde l'ancien mode, pour la validation des champs.
             * 
             * L'objet m�tier pour valider la cr�ation d'un client existe d�j�,
             * il est donc d�conseill� de dupliquer ici son contenu ! � la
             * place, il suffit de passer la requ�te courante � l'objet m�tier
             * existant et de r�cup�rer l'objet Client cr��.
             */
    	
    	
	        /*
	         * pour ne pas dupliquer l'objet Client : passer la requ�te courante � l'objet m�tier existant
	         * et r�cup�rer l'objet Client cr��
	         */
	        ClientCreationForm clientForm = new ClientCreationForm();
	        client = clientForm.createClient( request );
	
	        /*
	         * r�cup�rer le contenu de la map d'erreurs cr��e par l'objet m�tier ClientCreationForm
	         * dans la map d'erreurs courante (actuellement vide)
	         */
	        errors = clientForm.getErrors();
	        
        }

         
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
            result = "Succ�s de la cr�ation de la commande.";
        } else {
            result = "�chec de la cr�ation de la commande.";
        }
        return order;
    }

    private void dateValidation( String date ) throws Exception {
        if ( date == null ) {
            throw new Exception( "La date doit �tre renseign�e." );
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