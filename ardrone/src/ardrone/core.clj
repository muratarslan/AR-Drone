(ns ardrone.core
  (:import (javax.swing JFrame JPanel KeyStroke Action 
                        AbstractAction)
           (java.awt.event ActionEvent ActionListener)
           (java.awt Graphics Dimension Color)
           (java.awt.image BufferedImage))
  (:use [clj-drone.core]))

(drone-initialize)

(defn drone-forward []
  (drone :tilt-front 1))

(defn drone-back []
  (drone :tilt-back 1))

(defn drone-left []
  (drone :tilt-left 1))

(defn drone-right []
  (drone :tilt-right 1))

(defn take-off []
  (drone :take-off))

(defn drone-land []
  (drone :land))

(defn drone-emergency []
  (drone :emergency))

(defn droneup []
  (drone :up 1))

(defn dronedown []
  (drone :down 1))

(defn spin-left []
  (drone :spin-left 1))

(defn spin-right []
  (drone :spin-right 1))

(defn drone-yaw []
  (drone :anim-yaw-shake))

(defn drone-turn []
  (drone :anim-turnaround))

(defn drone-wave []
  (drone :anim-wave))

(defn drone-phi []
  (drone :anim-double-phi-theta-mixed))

(defn drone-flip []
  (drone :anim-flip-right))

(def forward
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Forward!!!")
      (drone-forward))))

(def backward
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Backward!!!") 
      (drone-back))))

(def left
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Left!!!")
      (drone-left))))

(def right
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Right!!!")
      (drone-right))))

(def takeoff
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Take Off") 
      (take-off))))

(def land
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Landing...")
      (drone-land))))

(def emergency
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Emergency...")
      (drone-emergency))))

(def d-up
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Up!")
      (droneup))))

(def d-down
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Down!")
      (dronedown))))

(def spinl
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Down!")
      (spin-left))))


(def spinr
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Down!")
      (spin-right))))

(def yaw-shake
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Down!")
      (drone-yaw))))

(def turn-around
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Down!")
      (drone-turn))))

(def anim-wave
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Down!")
      (drone-wave))))

(def double-phi
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Down!")
      (drone-phi))))

(def flip-right
  (proxy [AbstractAction ActionListener] []
    (actionPerformed [e] (println "Down!")
      (drone-flip))))



(def panel
  (doto
      (proxy [JPanel] []
        (paint [g] nil))
    (.setPreferredSize (new Dimension 200 200))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke "UP") "up"))
    (.. (getActionMap) (put "up" forward))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke "DOWN") "down"))
    (.. (getActionMap) (put "down" backward))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke "RIGHT") "right"))
    (.. (getActionMap) (put "right" right))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke "LEFT") "left"))
    (.. (getActionMap) (put "left" left))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \w) "dup"))
    (.. (getActionMap) (put "dup" d-up))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \s) "dwn"))
    (.. (getActionMap) (put "dwn" d-down))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \d) "spin"))
    (.. (getActionMap) (put "spin" spinr))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \a) "spn"))
    (.. (getActionMap) (put "spn" spinl))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \t) "to"))
    (.. (getActionMap) (put "to" takeoff))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \e) "emer"))
    (.. (getActionMap) (put "emer" emergency))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \l) "lnd"))
    (.. (getActionMap) (put "lnd" land))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \z) "yaw"))
    (.. (getActionMap) (put "yaw" yaw-shake))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \x) "turn"))
    (.. (getActionMap) (put "turn" turn-around))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \c) "wave"))
    (.. (getActionMap) (put "wave" anim-wave))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \v) "double"))
    (.. (getActionMap) (put "double" double-phi))
    (.. (getInputMap)  (put (KeyStroke/getKeyStroke \b) "flip"))
    (.. (getActionMap) (put "flip" flip-right))))

(def frame
  (doto
      (JFrame.)
    (.setDefaultCloseOperation JFrame/DISPOSE_ON_CLOSE)
    (.add panel)
    (.pack)
    (.setVisible true)))

(println "Fly me to the moon!")


;; https://github.com/gigasquid/clj-drone

;; https://github.com/christianblunden/drone-remote/blob/master/src/drone_remote/core.clj
