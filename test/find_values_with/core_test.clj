(ns find-values-with.core-test
  (:require [clojure.test :refer :all]
            [find-values-with.core :refer :all]))

(deftest a-test
  (testing "-main returns parsed JSON when given valid filepath"
    (is (= (-main "test/find_values_with/test-data/US_mini_10000011B1.json") {:items
                                                                              [{:id 1
                                                                                :item-type "feature"
                                                                                :value "motor manufactured part"
                                                                                :value-type "text"
                                                                                :importance "auto"
                                                                                :items [{:id 610, :item-type "feature", :value "distortion", :value-type "text", :importance "auto", :items []}]}
                                                                               {:id 14, :item-type "feature", :value "tray", :value-type "text", :importance "auto", :items []}
                                                                               {:id 15, :item-type "feature", :value "remaining binder", :value-type "text", :importance "auto", :items []}
                                                                               {:id 19
                                                                                :item-type "feature"
                                                                                :value "apparatus of similar advantage"
                                                                                :value-type "text"
                                                                                :importance "auto"
                                                                                :items
                                                                                [{:id 614
                                                                                  :item-type "feature"
                                                                                  :value "print head"
                                                                                  :value-type "text"
                                                                                  :importance "auto"
                                                                                  :items
                                                                                  [{:id 655, :item-type "feature", :value "polyjet”", :value-type "text", :importance "auto", :items []}
                                                                                   {:id 656
                                                                                    :item-type "feature"
                                                                                    :value "liquid colloid suspension or powder jetting devices"
                                                                                    :value-type "text"
                                                                                    :importance "auto"
                                                                                    :items []}]}
                                                                                 {:id 889
                                                                                  :item-type "feature"
                                                                                  :value "layers of second material"
                                                                                  :value-type "text"
                                                                                  :importance "auto"
                                                                                  :items []}]}]}))))
