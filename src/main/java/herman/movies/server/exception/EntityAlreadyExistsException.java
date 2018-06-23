package herman.movies.server.exception;

/**
 * Created by herman.kurniawan on 6/23/2018.
 */
public class EntityAlreadyExistsException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public EntityAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
