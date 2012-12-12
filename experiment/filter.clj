(ns experiment.filter
  (:require [clj-http.client :as client])
  (:import java.net.URL)
  (:require [net.cgrand.enlive-html :as enlive])
  (:import java.io.StringReader))

(def resp (slurp "experiment/index.html"))

(println "Page size: " (count resp))
(def channels (-> resp (StringReader.) (enlive/html-resource)
           (enlive/select
              [:#head-content :ul.nav :ul.wide :> [:li (enlive/has [[:a (enlive/attr= :href "#")]])]])))
(println channels)
