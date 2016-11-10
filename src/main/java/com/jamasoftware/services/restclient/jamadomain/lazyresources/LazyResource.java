package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.UnexpectedJamaResponseException;
import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.exception.RestClientException;

public abstract class LazyResource implements JamaDomainObject {
    private JamaInstance jamaInstance;
    private Integer id;
    private boolean fetched = false;

    public void fetch() {
        try {
            if (!fetched && id != null) {
                copyContentFrom(jamaInstance.getResource(getResourceUrl()));
                fetched = true;
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getResourceUrl();

    protected abstract void copyContentFrom(JamaDomainObject jamaDomainObject);

    public void associate(int id, JamaInstance jamaInstance) {
        this.id = id;
        this.jamaInstance = jamaInstance;
    }

    public Integer getId() {
        return id;
    }

    public JamaInstance getJamaInstance() {
        return jamaInstance;
    }

    public boolean isAssociated() {
        return id != null && jamaInstance != null;
    }

    public void checkType(Class clazz, JamaDomainObject jamaDomainObject) {
        if(!clazz.isInstance(jamaDomainObject)) {
            throw new UnexpectedJamaResponseException("Expecting a " + clazz.getName() + " from the Jama server. Instead, got: " + jamaDomainObject.getClass().getName());
        }
    }
}
