(ns dilist.filter_test
  (:use clojure.test)
  (:use dilist.filter)
  (:require [dilist.stream :as stream])
  )

(def premiumStream (stream/map->Stream {:format "MP3" :name "prem1" :url "/premium"}))
(def regularStream (stream/map->Stream {:format "MP3" :name "reg1" :url "http://di.fm/stream/example.m3u"}))

(deftest premiumEntriesAreRemoved
  (testing "premium entries are removed for lists of streams"
           (is (not (seq (noPremium [premiumStream])))))
  (testing "premium entries are removed for lists of streams, regulars are left alone"
           (is (= (cons regularStream nil) (noPremium [premiumStream regularStream]))))
  )

