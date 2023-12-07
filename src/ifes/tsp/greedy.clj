(ns ifes.tsp.greedy
  (:require [ifes.tsp.tsp-core :as tsp-core])
  (:gen-class)
)

; Método para calcular a melhor rota
; Método para buscar o ponto de partida
; Método para

(defn proximas-rotas
  [mapa cidade-atual cidades-restantes]
  (map
    (fn [proxima-cidade] {:proxima-cidade proxima-cidade :custo (tsp-core/distancia mapa cidade-atual proxima-cidade)})
    cidades-restantes
  )
)

(defn melhor-cidade
  [rotas]
  (apply min-key :custo rotas)
)

(def primeira-rota [(inc (rand-int (dec tsp-core/num-cidades)))])

(defn remover-cidade
  [cidade cidades]
  (remove #(= cidade %) cidades)
)

(def cidades (remover-cidade (first primeira-rota) (range 1 (inc tsp-core/num-cidades))))

(defn proxima-melhor-cidade
  [mapa rota cidades-restantes]
  (get (melhor-cidade (tsp-core/rotas-validas(proximas-rotas mapa (last rota) cidades-restantes))) :proxima-cidade)
)

(defn melhor-rota
  [mapa rota cidades-restantes]
  (if (> (count cidades-restantes) 1)
    (recur mapa (conj rota (proxima-melhor-cidade mapa rota cidades-restantes)) (remover-cidade (proxima-melhor-cidade mapa rota cidades-restantes) cidades-restantes))
    (conj rota (first cidades-restantes))
  )
)
