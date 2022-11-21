// IHellAidlInterface.aidl
package com.itis.template;

// Declare any non-default types here with import statements

import com.itis.template.AsyncCallback;
import com.itis.template.Song;


interface IHellAidlInterface {

    void sum(int a, int b);

    // oneway - говорит о том, что функция будет выполняться в другом потоке
    oneway void sumAsync(int a, int b, AsyncCallback callback);

    void playMusic();

    void pauseMusic();

    void setMusic(in Song song);

    void setMusicFromBundle(in Bundle bundle);

}
