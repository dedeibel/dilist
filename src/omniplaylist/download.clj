(ns omniplaylist.download
  (:require [clj-http.client :as http])) 

(def ^:dynamic *user-agent* "Mozilla/5.0 (Windows NT 6.1;) Gecko/20100101 Firefox/13.0.1")

(defn as-stream [url] 
  (let
    [response (http/get url
                        {:as :stream, :headers {"User-Agent" *user-agent*}})]
    (if (= 200 (:status response))
      (:body response)
      (throw (Exception. (str "Could not fetch web page, return http code: " (:status response)))))))

