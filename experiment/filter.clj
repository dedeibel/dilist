(ns experiment.filter
  (:require [clj-http.client :as client])
  (:import java.net.URL)
  (:require [net.cgrand.enlive-html :as enlive])
  (:import java.io.StringReader)
  (:use clojure.pprint))

(def resp (slurp "index.html"))

(println "Page size: " (count resp))
(def channels (-> resp (StringReader.) (enlive/html-resource)
                  (enlive/select
                    [:.main :div.channels :div.lists :> :ul :> :li :> :a])))
(def chans (for [entry channels]
             (let [path-name    (subs (-> entry :attrs :href) 1)
                   display-name (-> entry :content (nth 3) :content first)
                   pls-url      (str "http://listen.di.fm/public2/" path-name ".pls")]
                [display-name path-name pls-url])))
(pprint chans)
