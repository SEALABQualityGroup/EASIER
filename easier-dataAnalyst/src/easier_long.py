#easier_long

import numpy as np
import myfunction
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

base_dir = '../data/'

# Near-Pareto frontier from a chromosome long 4
pareto_folder_64_l4 = base_dir + 'FTA/64/lenght_4/pareto/P_64_E_1280_X_0.8_M_0.2__18.10.03.11.03.58__/'
file_64_l4 = pareto_folder_64_l4 + 'P_64_E_1280_X_0.8_M_0.2_solutions.csv'
pareto_64_l4 = [1004, 707, 801]
data_64_l4 = np.loadtxt(open(file_64_l4, "rb"), delimiter=";")  # , skiprows=1)
min_pa_64_l4 = min(data_64_l4[:, 1])
max_pa_64_l4 = max(data_64_l4[:, 1])
min_perfq_64_l4 = min(data_64_l4[:, 2])
max_perfq_64_l4 = max(data_64_l4[:, 2])
min_dist_64_l4 = min(data_64_l4[:, 3])
max_dist_64_l4 = max(data_64_l4[:, 3])

# Near-Pareto frontier from a chromosome long 6
pareto_folder_64_l6 = base_dir + 'FTA/64/lenght_6/pareto/P_64_E_1280_X_0.8_M_0.2__18.10.03.19.25.59__/'
file_64_l6 = pareto_folder_64_l6 + 'P_64_E_1280_X_0.8_M_0.2_solutions.csv'
pareto_64_l6 = [1386, 1405, 1615, 1760, 355, 775]
data_64_l6 = np.loadtxt(open(file_64_l6, "rb"), delimiter=";")  # , skiprows=1)
min_pa_64_l6= min(data_64_l6[:, 1])
max_pa_64_l6 = max(data_64_l6[:, 1])
min_perfq_64_l6 = min(data_64_l6[:, 2])
max_perfq_64_l6 = max(data_64_l6[:, 2])
min_dist_64_l6 = min(data_64_l6[:, 3])
max_dist_64_l6 = max(data_64_l6[:, 3])

# Near-Pareto frontier from a chromosome long 8
pareto_folder_64_l8 = base_dir + 'FTA/64/lenght_8/pareto/P_64_E_1280_X_0.8_M_0.2__18.10.04.06.45.58__/'
file_64_l8 = pareto_folder_64_l8 + 'P_64_E_1280_X_0.8_M_0.2_solutions.csv'
pareto_64_l8 = [1238, 1409, 1603, 2046, 2246, 843]
data_64_l8 = np.loadtxt(open(file_64_l8, "rb"), delimiter=";")  # , skiprows=1)
min_pa_64_l8= min(data_64_l8[:, 1])
max_pa_64_l8 = max(data_64_l8[:, 1])
min_perfq_64_l8 = min(data_64_l8[:, 2])
max_perfq_64_l8 = max(data_64_l8[:, 2])
min_dist_64_l8 = min(data_64_l8[:, 3])
max_dist_64_l8 = max(data_64_l8[:, 3])



# normalize PA with respect to PerfQ
#data_64_l4[:, 1] = myfunction.norm2perfq(min_perfq_64_l4, max_perfq_64_l4, data_64_l4[:, 1], min_pa_64_l4, max_pa_64_l4)


# normalize Dist with respect to PerfQ
#data_64_l4[:,3] = myfunction.norm2perfq(min_perfq_64_l4, max_perfq_64_l4, data_64_l4[:,3], min_dist_64_l4, max_dist_64_l4)


# scale values
for i in range(1, 3):
   # mean-center data
   data_64_l4[:, i] -= data_64_l4[:, i].mean()
   data_64_l6[:, i] -= data_64_l6[:, i].mean()
   data_64_l8[:, i] -= data_64_l8[:, i].mean()

   # divide by the standard deviation
   data_64_l4[:, i] /= data_64_l4[:, i].std()
   data_64_l6[:, i] /= data_64_l6[:, i].std()
   data_64_l8[:, i] /= data_64_l8[:, i].std()


# delta = max(distances)
delta = 0.2

fig = plt.figure(figsize=(30, 20))
fig.suptitle('easier_long', fontsize=16)
ax = fig.add_subplot(111, projection='3d')
ax.view_init(25, 15)
ax.set_xlabel('PA')
ax.set_ylabel('PerfQ')
ax.set_zlabel('Dist')

# pareto solutions and close solutions (delta)
myfunction.plot_pareto(ax, data_64_l4, pareto_64_l4, 's', 'r', 'pareto_plot_64_l4')
myfunction.scatter_near_solution(ax, data_64_l4, pareto_64_l4, 's', '#000000', 'solutions_64_l4', delta)

myfunction.plot_pareto(ax, data_64_l6, pareto_64_l6, 'o', 'green', 'pareto_plot_64_l6')
myfunction.scatter_near_solution(ax, data_64_l6, pareto_64_l6, 'o', 'b', 'solutions_64_l6', delta)

myfunction.plot_pareto(ax, data_64_l8, pareto_64_l8, '^', 'black', 'pareto_plot_64_l8')
myfunction.scatter_near_solution(ax, data_64_l8, pareto_64_l8, '^', 'black', 'solutions_64_l8', delta)

ax.legend()
plt.show()