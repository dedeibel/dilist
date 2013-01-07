(ns dilist.download
  (:require [clj-http.client :as http])) 

(def ^:dynamic *userAgent* "Mozilla/5.0 (Windows NT 6.1;) Gecko/20100101 Firefox/13.0.1")
(def ^:dynamic *digitallyImportedURL* "http://di.fm")

(defn difmPageAsStream [] 
  (let
    [response (http/get *digitallyImportedURL*
                        {:as :stream, :decode-body-headers true, :headers {"User-Agent" *userAgent*}})]
    (if (= 200 (:status response))
      (:body response)
      (throw (Exception. (str "Could not fetch di.fm web page, return http code: " (:status response)))))))
  
