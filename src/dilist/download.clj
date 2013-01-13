(ns dilist.download
  (:require [clj-http.client :as http])) 

(def ^:dynamic *user-agent* "Mozilla/5.0 (Windows NT 6.1;) Gecko/20100101 Firefox/13.0.1")
(def ^:dynamic *digitally-imported-uRL* "http://di.fm")

(defn difm-page-as-stream [] 
  (let
    [response (http/get *digitally-imported-uRL*
                        {:as :stream, :decode-body-headers true, :headers {"User-Agent" *user-agent*}})]
    (if (= 200 (:status response))
      (:body response)
      (throw (Exception. (str "Could not fetch di.fm web page, return http code: " (:status response)))))))
  
