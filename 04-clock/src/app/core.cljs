;; create main project namespace
(ns app.core
  (:require-macros [app.macros :refer [p pp]]))

;; globals

(def second 1000)

(def minute 60000)

(def hour 3600000)


;; Event handlers

(defn set-seconds-hand []
  (let [now (js/Date.)
        seconds (.getSeconds now)
        secondsAsDegrees (+ (* (/ seconds 60) 360) 90)
        transformVal (str "rotate(" secondsAsDegrees "deg)")]
    (set! (.-transform (.-style (.querySelector js/document ".hour-hand"))) transformVal)))


(defn set-minute-hand []
  (let [now (js/Date.)
        minutes (.getMinutes now)
        minutesAsDegrees (+ (* (/ minutes 60) 360) 90)
        transformVal (str "rotate(" minutesAsDegrees "deg)")]
    (set! (.-transform (.-style (.querySelector js/document ".min-hand"))) transformVal)))


(defn set-hour-hand []
  (let [now (js/Date.)
        hours (.getHours now)
        hoursAsDegrees (+ (* (/ hours 12) 360) 90)
        transformVal (str "rotate(" hoursAsDegrees "deg)")]
    (set! (.-transform (.-style (.querySelector js/document ".second-hand"))) transformVal)))


;; start

(js/setInterval #(set-seconds-hand) second)
(js/setInterval #(set-minute-hand) minute)
(js/setInterval #(set-hour-hand) hour)