#easier_long

import numpy as np
import myfunction
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

base_dir = '../data/'

# Near-Pareto frontier from a chromosome long 4
pareto_folder_64_E1280 = base_dir + 'FTA/64/lenght_4/pareto/P_64_E_1280_X_0.8_M_0.2__18.10.03.11.03.58__/'
file_64_E1280 = pareto_folder_64_E1280 + 'P_64_E_1280_X_0.8_M_0.2_solutions.csv'
pareto_64_E1280 = [1004, 707, 801]
data_64_E1280 = np.loadtxt(open(file_64_E1280, "rb"), delimiter=";")  # , skiprows=1)
min_pa_64_E1280 = min(data_64_E1280[:, 1])
max_pa_64_E1280 = max(data_64_E1280[:, 1])
min_perfq_64_E1280 = min(data_64_E1280[:, 2])
max_perfq_64_E1280 = max(data_64_E1280[:, 2])
min_dist_64_E1280 = min(data_64_E1280[:, 3])
max_dist_64_E1280 = max(data_64_E1280[:, 3])

# Near-Pareto frontier from a chromosome long 6
pareto_folder_64_E1600 = base_dir + 'FTA/64/lenght_4/pareto/P_64_E_1600_X_0.8_M_0.2__18.10.03.11.47.23__/'
file_64_E1600 = pareto_folder_64_E1600 + 'P_64_E_1600_X_0.8_M_0.2_solutions.csv'
pareto_64_E1600 = [2027, 2048, 2155, 2225, 2819, 576]
data_64_E1600 = np.loadtxt(open(file_64_E1600, "rb"), delimiter=";")  # , skiprows=1)
min_pa_64_E1600= min(data_64_E1600[:, 1])
max_pa_64_E1600 = max(data_64_E1600[:, 1])
min_perfq_64_E1600 = min(data_64_E1600[:, 2])
max_perfq_64_E1600 = max(data_64_E1600[:, 2])
min_dist_64_E1600 = min(data_64_E1600[:, 3])
max_dist_64_E1600 = max(data_64_E1600[:, 3])

# Near-Pareto frontier from a chromosome long 8
pareto_folder_64_E960 = base_dir + 'FTA/64/lenght_4/pareto/P_64_E_960_X_0.8_M_0.2__18.10.03.10.16.12__/'
file_64_E960 = pareto_folder_64_E960 + 'P_64_E_960_X_0.8_M_0.2_solutions.csv'
pareto_64_E960 = [1026, 595, 706, 782]
data_64_E960 = np.loadtxt(open(file_64_E960, "rb"), delimiter=";")  # , skiprows=1)
min_pa_64_E960= min(data_64_E960[:, 1])
max_pa_64_E960 = max(data_64_E960[:, 1])
min_perfq_64_E960 = min(data_64_E960[:, 2])
max_perfq_64_E960 = max(data_64_E960[:, 2])
min_dist_64_E960 = min(data_64_E960[:, 3])
max_dist_64_E960 = max(data_64_E960[:, 3])

# Near-Pareto frontier from a chromosome long 8
pareto_folder_64_E640 = base_dir + 'FTA/64/lenght_4/pareto/P_64_E_640_X_0.8_M_0.2__18.10.03.09.49.28__/'
file_64_E640 = pareto_folder_64_E640 + 'P_64_E_640_X_0.8_M_0.2_solutions.csv'
pareto_64_E640 = [257, 803, 810, 815]
data_64_E640 = np.loadtxt(open(file_64_E640, "rb"), delimiter=";")  # , skiprows=1)
min_pa_64_E640= min(data_64_E960[:, 1])
max_pa_64_E640 = max(data_64_E960[:, 1])
min_perfq_64_E640 = min(data_64_E960[:, 2])
max_perfq_64_E640 = max(data_64_E960[:, 2])
min_dist_64_E640 = min(data_64_E960[:, 3])
max_dist_64_E640 = max(data_64_E960[:, 3])



# normalize PA with respect to PerfQ
#data_64_E1280[:, 1] = myfunction.norm2perfq(min_perfq_64_E1280, max_perfq_64_E1280, data_64_E1280[:, 1], min_pa_64_E1280, max_pa_64_E1280)


# normalize Dist with respect to PerfQ
#data_64_E1280[:,3] = myfunction.norm2perfq(min_perfq_64_E1280, max_perfq_64_E1280, data_64_E1280[:,3], min_dist_64_E1280, max_dist_64_E1280)


# scale values
for i in range(1, 3):
   # mean-center data
   data_64_E1280[:, i] -= data_64_E1280[:, i].mean()
   data_64_E1600[:, i] -= data_64_E1600[:, i].mean()
   data_64_E960[:, i] -= data_64_E960[:, i].mean()
   data_64_E640[:, i] -= data_64_E640[:, i].mean()

   # divide by the standard deviation
   data_64_E1280[:, i] /= data_64_E1280[:, i].std()
   data_64_E1600[:, i] /= data_64_E1600[:, i].std()
   data_64_E960[:, i] /= data_64_E960[:, i].std()
   data_64_E640[:, i] /= data_64_E640[:, i].std()


# delta = max(distances)
delta = 0.2

fig = plt.figure(figsize=(30, 20))
fig.suptitle('easier_evolution', fontsize=16)
ax = fig.add_subplot(111, projection='3d')
ax.view_init(25, 15)
ax.set_xlabel('PA')
ax.set_ylabel('PerfQ')
ax.set_zlabel('Dist')

# pareto solutions and close solutions (delta)
myfunction.plot_pareto(ax, data_64_E640, pareto_64_E640, '+', '#ff9933', 'pareto_plot_64_E640')
# myfunction.scatter_near_solution(ax, data_64_E640, pareto_64_E640, '+', 'grey', 'solutions_64_E640', delta)

myfunction.plot_pareto(ax, data_64_E960, pareto_64_E960, '^', 'b', 'pareto_plot_64_E960')
# myfunction.scatter_near_solution(ax, data_64_E960, pareto_64_E960, '^', 'green', 'solutions_64_E960', delta)

myfunction.plot_pareto(ax, data_64_E1280, pareto_64_E1280, 's', 'r', 'pareto_plot_64_E1280')
# myfunction.scatter_near_solution(ax, data_64_E1280, pareto_64_E1280, 's', '#000000', 'solutions_64_E1280', delta)

myfunction.plot_pareto(ax, data_64_E1600, pareto_64_E1600, 'o', 'green', 'pareto_plot_64_E1600')
# myfunction.scatter_near_solution(ax, data_64_E1600, pareto_64_E1600, 'o', 'b', 'solutions_64_E1600', delta)


ax.legend()
plt.show()
