(ns dilist.filter_test
  (:use clojure.test)
  (:use dilist.filter)
  (:require [dilist.stream :as stream])
  )

(def premium-stream (stream/map->Stream {:format "MP3" :name "prem1" :url "/premium"}))
(def regular-stream (stream/map->Stream {:format "MP3" :name "reg1" :url "http://di.fm/stream/example.m3u"}))

(deftest premium-entries-are-removed
  (testing "premium entries are removed for lists of streams"
           (is (not (seq (no-premium [premium-stream])))))
  (testing "premium entries are removed for lists of streams, regulars are left alone"
           (is (= (cons regular-stream nil) (no-premium [premium-stream regular-stream]))))
  )

