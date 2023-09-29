package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class XUrlImpl implements XUrl{

    private Map<String,String> longToShortUrl=new HashMap<>();
    private Map<String,String> shortToLongUrl=new HashMap<>();
    private Map<String,Integer> hitCountMap=new HashMap<>();

    @Override
    public String registerNewUrl(String longUrl) {
        String URL="";
        if(longToShortUrl.containsKey(longUrl)){
           URL=longToShortUrl.get(longUrl);
            return URL;
        }else{
            String shortUrl=generateShortUrl();
            longToShortUrl.put(longUrl, shortUrl);
            shortToLongUrl.put(shortUrl, longUrl);
           hitCountMap.put(longUrl,0);
           URL= shortUrl;  
            
        }
       
       return URL;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        String URL="";
        if(shortToLongUrl.containsKey(shortUrl)){
            return null;
        }else{
            longToShortUrl.put(longUrl, shortUrl);
            shortToLongUrl.put(shortUrl, longUrl);
            hitCountMap.put(longUrl, 0);
            URL= shortUrl;
        }

        return URL;
    }

    @Override
    public String getUrl(String shortUrl) {
        String URL="";
            if (shortToLongUrl.containsKey(shortUrl)) {
                String longUrl = shortToLongUrl.get(shortUrl);
                hitCountMap.put(longUrl, hitCountMap.get(longUrl) + 1);
                URL= longUrl;
            } else {
                return null;
            }
             return URL;
        }
    

    @Override
    public Integer getHitCount(String longUrl) {
        return hitCountMap.getOrDefault(longUrl, 0);
       
    }

    @Override
    public String delete(String longUrl) {
        String URL="";
            if (longToShortUrl.containsKey(longUrl)) {
                String shortUrl = longToShortUrl.remove(longUrl);
                shortToLongUrl.remove(shortUrl);
                URL= shortUrl;
            } else {
                return null;
            }
            return URL;
        }


    private String generateShortUrl() {
        Random random=new Random();
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortUrl=new StringBuilder(9);
        for(int i=0;i<9;i++){
            int intRandomIndex=random.nextInt(allowedCharacters.length());
            shortUrl.append(allowedCharacters.charAt(intRandomIndex));
        }
        return "http://short.url/" + shortUrl.toString();

        }

    }

