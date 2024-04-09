package com.osu.venglar.EventHarbor.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Neporadrilo se najit uzivatele s id "+ id);
    }
}
