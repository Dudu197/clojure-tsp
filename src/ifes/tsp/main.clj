(ns ifes.tsp.main
  (:require [ifes.tsp.tsp-core :as tsp-core]
            [ifes.tsp.brute_force :as brute-force]
            [ifes.tsp.greedy :as greedy]
            [jsonista.core :as json]
            [clojure.core.match :refer [match]]
            )
  (:gen-class))

(defn imprimir-mapa
  [mapa]
  (println "Mapa gerado:")
  (doseq [i (sort (keys mapa))] (println i ":" (get mapa i)))
)

(defn executar-forca-bruta
  [mapa]
  (println "Executando algoritmo força bruta")
  (def start-time (System/currentTimeMillis))
  (def resultado (brute-force/melhor-rota mapa (brute-force/gerar-rotas)))
  (def end-time (System/currentTimeMillis))
  (println resultado)
  (println "Força bruta executado em:" (- end-time start-time) "ms")
)

(defn executar-guloso
  [mapa]
  (println "Executando algoritmo guloso")
  (def start-time (System/currentTimeMillis))
  (def resultado (greedy/melhor-rota mapa))
  (def end-time (System/currentTimeMillis))
  (println resultado)
  (println "Algoritmo guloso executado em:" (- end-time start-time) "ms")
)

(defn imprimir-instrucoes []
  (println "Nenhum argumento foi informado")
  (println "Para executar, utilize os seguintes parâmetros em sequência:")
  (println "Algoritmo (obrigatório): informe qual algoritmo será executado. Opções: bruteforce, greedy, both")
  (println "Mapa (opcional): informe um arquivo json para ser utilizado como mapa. Incompatível se o número de cidades for fornecido")
  (println "Cidades (opcional): informe o número de cidades a ser gerado. Incompatível se o mapa for fornecido")
  (println "Distância máxima (opcional): informe a distância máxima entre as cidades. Incompatível se o mapa for fornecido. Obrigatório de o número de cidades for fornecido")
  (println "")
  (println "Exemplos:")
  (println "lein run both resources/ex01.json")
  (println "lein run greedy 10 50 9")
)

(defn executar-algoritmo
  [algoritmo mapa]
  (imprimir-mapa mapa)
  (match algoritmo
     "bruteforce" (executar-forca-bruta mapa)
     "greedy" (executar-guloso mapa)
     "both" [(executar-forca-bruta mapa) (executar-guloso mapa)]
  )
)

(defn executar-json
  [algoritmo arquivo]
  (let [[conf] [(json/read-value (slurp arquivo))]]
    (tsp-core/definir-num-cidades (get conf "cidades"))
    (tsp-core/definir-distancia-maxima (get conf "distancia"))
    (tsp-core/definir-num-conexoes (dec (get conf "cidades")))
    (executar-algoritmo algoritmo (get conf "mapa"))
  )
)

(defn executar-parametros
  [algoritmo cidades distancia]
  (tsp-core/definir-num-cidades cidades)
  (tsp-core/definir-distancia-maxima distancia)
  (tsp-core/definir-num-conexoes (dec cidades))
  (executar-algoritmo algoritmo (tsp-core/gerar-cidades-seguro))
)

(defn -main
  [& args]
  (match (count args)
     0 (imprimir-instrucoes)
     2 (executar-json (nth args 0) (nth args 1))
     3 (executar-parametros (nth args 0) (Integer/parseInt (nth args 1)) (Integer/parseInt (nth args 2)))
  )
)
