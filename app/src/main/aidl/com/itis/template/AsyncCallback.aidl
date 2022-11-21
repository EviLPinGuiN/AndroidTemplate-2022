// AsyncCallback.aidl
package com.itis.template;

import com.itis.template.AidlException;
import com.itis.template.Song;

// Declare any non-default types here with import statements

interface AsyncCallback {
    void onSuccess(in Song song);
    void onError(in AidlException error);
}