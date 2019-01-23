#myfunction.py

import numpy as np
from math import pow, sqrt
from operator import itemgetter


def norm2perfq(min_perfq, max_perfq, value, min, max):
    return min_perfq + (max_perfq - min_perfq) * (value - min) / (max - min)


def getpoint(data, index):
    try:
        return data[np.where(data[:, 0] == index)[0][0], 1:4]
    except IndexError:
        print(index)
        return None


def distance(a, b):
    return sqrt(pow((a[0] - b[0]), 2) + pow((a[1] - b[1]), 2) + pow((a[2] - b[2]), 2))


def distance_from_perfq(a, b):
    return abs(a[1] - b[1])


def remove_pareto_solutions(data, pareto):
    return {sol[0]: min([distance(sol[1:4], getpoint(data, par)) for par in pareto]) for sol in data}


def calculate_distance_from_pareto(data, pareto):
    return {sol[0]: min([distance(sol[1:4], getpoint(data, par)) for par in pareto]) for sol in data}


def scatter_near_solution(ax, data, pareto, m, c, label='', delta=5):
    distance_from_pareto = calculate_distance_from_pareto(data, pareto)
    neighbours = {k: v for k, v in distance_from_pareto.items() if v != 0 and v <= delta}

    matrix = np.matrix([getpoint(data, sol) for sol in neighbours])
    if matrix.size == 0:
        print('No close solutions in delta = '+str(delta))
    else:
        ax.scatter(matrix[:, 0], matrix[:, 1], matrix[:, 2], c=c, marker=m, label=label)


def plot_pareto(ax, data, pareto, m, c, l):
    # ax.plot(pareto[:, 0], pareto[:, 1], pareto[:, 2], c=color, marker=marker)
    matrix = get_sorted_matrix(data, pareto)
    ax.scatter(matrix[:, 0], matrix[:, 1], matrix[:, 2], c=c, marker=m)
    ax.plot(matrix[:, 0].A1, matrix[:, 1].A1, matrix[:, 2].A1, c=c, label=l)


def get_sorted_matrix(data, sol):
    return np.matrix(sorted([getpoint(data, s) for s in sol], key=itemgetter(0)))
