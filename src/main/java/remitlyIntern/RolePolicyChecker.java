package remitlyIntern;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import  java.util.regex.Pattern;

public class RolePolicyChecker {
    private final Pattern policyNamePatttern = Pattern.compile("^([\\w+=,.@-]){1,128}$");
    private final Set<String> validRolePolicyAttr = new HashSet<>(Arrays.asList(new String[]{"PolicyName","PolicyDocument"}));
    private final Set<String> validPolicyDocument = new HashSet<>(Arrays.asList(new String[]{"Version","Statement"}));
    private final List<String> mandatoryStatementAttr = new ArrayList<>(Arrays.asList(new String[]{"Effect","Action","Resource"}));
    private final List<String> stringOrList = new ArrayList<>(Arrays.asList(new String[]{"Action","Resource","NotResource","NotAction"}));
    private final List<String> mustBeString = new ArrayList<>(Arrays.asList(new String[]{"Sid","Effect"}));
    private final List<String> principalOrCondition = new ArrayList<>(Arrays.asList(new String[]{"Principal","NotPrincipal","Condition"}));

    public RolePolicyChecker(){}
    public boolean verifyPolicy(String json){
        JSONObject rolePolicy = new JSONObject(json);
        if(rolePolicy.keySet().equals(validRolePolicyAttr)) {
            if(!checkPolicyName(rolePolicy.get("PolicyName"))){
                throw new RuntimeException("PolicyName missing");
            }
            if(!checkPolicyDocument((JSONObject) rolePolicy.get("PolicyDocument"))){
                return false;
            }
            return true;
        }
        else throw new RuntimeException("Incorrect format of PolicyDocument");
    }

    private boolean checkPolicyName(Object policyName){
        return policyName instanceof String && policyNamePatttern.asMatchPredicate().test((String) policyName);
    }

    private boolean checkPolicyDocument(JSONObject policyDocument){
        if(!policyDocument.keySet().equals(validPolicyDocument))
            throw new RuntimeException("Incorrect format of PolicyDocument. Expected: "+validPolicyDocument+" Actual: "+policyDocument.keySet());
        //CHECK IF DOCUMENT CONTAINS VERSION
        if(policyDocument.get("Version") instanceof String) {
            //CHECK IF DOCUMENT CONTAINS STATEMENT
            if(policyDocument.get("Statement") instanceof JSONArray) {
                //CHECK EACH STATEMENT
                JSONArray statements = policyDocument.getJSONArray("Statement");
                return checkStatements(statements);
            }
            else
                throw new RuntimeException("Statement is incorrect, expected JSONArray. Got: "+policyDocument.get("Statement").getClass());
        }
        else
            throw new RuntimeException("Version is incorrect, expected String. Got: "+policyDocument.get("Version").getClass());
    }

    private boolean checkStatements(JSONArray statements){
        //CHECK EACH STATEMENT FORMAT, if incorrect it will throw exception
        for (Object statement : statements) {
            checkStatementFormat((JSONObject) statement);
        }
        //IF ALL STATEMENTS ARE CORRECT RETURN TRUE
        //CHECK EACH STATEMENT RESOURCE
        for (Object statement : statements) {
            if(!checkResource((JSONObject) statement))
                return false;
        }
        return true;
    }

    private void checkStatementFormat(JSONObject statement) throws RuntimeException {
        Set<String> keys = statement.keySet();
        if(!keys.containsAll(mandatoryStatementAttr))
            throw new RuntimeException("Missing required parameter, required are: "+mandatoryStatementAttr);
        for(String key : statement.keySet()) {
            Object obj = statement.get(key);
            if (stringOrList.contains(key)) {
                if(!checkIfString(obj) && !checkIfStringList(obj))
                    throw new RuntimeException("INVALID STATEMENT " + key);
            }
            else if (mustBeString.contains(key)) {
                if(!checkIfString(obj))
                    throw new RuntimeException("INVALID STATEMENT " + key);
            }
            else if (principalOrCondition.contains(key)) {
                if(!checkPrincipalOrCondition(obj))
                    throw new RuntimeException("INVALID STATEMENT " + key);
            }
            else
                throw new RuntimeException("INVALID STATEMENT " + key);
        }
    }
    private boolean checkResource(JSONObject statement) {
        if (statement.has("Resource")) {
            Object resources = statement.get("Resource");
            return !(resources instanceof String) || !"*".equals((String) resources);
        }
        else
            return true;
    }

    private  boolean checkIfString(Object obj){
        return obj instanceof String;
    }
    private boolean checkIfStringList(Object obj){
        if(obj instanceof JSONArray array){
            for(Object element : array)
                if (!checkIfString(element))
                    return false;
            return true;
        }
        return false;
    }

    private boolean checkPrincipalOrCondition(Object obj){
        //check if condition block is JSONObject
        if(obj instanceof JSONObject json){
            //check each condition in the block
            for(String key: json.keySet()) {
                if( json.get(key) instanceof JSONObject cond){
                    if(cond.keySet().size() == 1){
                        if(!checkIfString(cond.keys().next()) && !checkIfStringList(cond.keys().next()))
                            return false;
                    }
                    else return false;
                }
                else return false;
            }
            return true;
        }
        return false;
    }

}
