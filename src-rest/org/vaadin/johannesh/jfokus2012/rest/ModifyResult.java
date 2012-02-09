package org.vaadin.johannesh.jfokus2012.rest;

public class ModifyResult {

    private final String code;
    private final Long resourceId;

    public static final String SUCCESS = "OK";
    public static final String ERROR = "ERROR";
    public static final ModifyResult RESULT_ERROR = new ModifyResult(ERROR,
            null);

    public ModifyResult(String code, Long resourceId) {
        this.code = code;
        this.resourceId = resourceId;
    }

    public String getCode() {
        return code;
    }

    public Long getResourceId() {
        return resourceId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result
                + ((resourceId == null) ? 0 : resourceId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModifyResult other = (ModifyResult) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (resourceId == null) {
            if (other.resourceId != null)
                return false;
        } else if (!resourceId.equals(other.resourceId))
            return false;
        return true;
    }

}
