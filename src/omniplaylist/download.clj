(ns omniplaylist.download
  (require [clj-http.client :as http]
           [clojure.java.io :as io]))

; Use the following for more debug information
; (:use clojure.tools.logging
;        clojure.pprint
;        clj-logging-config.log4j)
;
;(set-loggers!
;  "org.apache.http"
;  {:level :debug})

(def ^:dynamic *user-agent* "Mozilla/5.0 (Windows NT 6.1;) Gecko/20100101 Firefox/13.0.1")

(defn as-stream [url] 
  (let
    [response (http/get url
                        {:as :stream, :headers {"User-Agent" *user-agent*}})]
    (if (= 200 (:status response))
      (:body response)
      (throw (Exception. (str "Could not fetch web page, return http code: " (:status response)))))))

(defn process-parallel-with-connection-pool [fun urls]
  (http/with-connection-pool {:timeout 10 :threads 8 :default-per-route 8}
    (doall (pmap fun urls))))

(defn- run-function-on-body-stream [fun]
  (fn [url]
    (with-open [body (as-stream url)]
      (fun body))))

(defn process-as-streams [fun urls ]
  (process-parallel-with-connection-pool
    #(with-open [body (as-stream %)]
       (fun body))
    urls))

