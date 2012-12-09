(ns parseStart
  (:require [clj-http.client :as client])
  (:require [net.cgrand.enlive-html :as enlive])
  (:import java.net.URL)
  (:import java.io.StringReader))

(def resp (slurp "index.html"))

(println "Page size: " (count resp))
(println (-> resp (StringReader.) (enlive/html-resource) (enlive/select [:body :img]) first :attrs :src))
