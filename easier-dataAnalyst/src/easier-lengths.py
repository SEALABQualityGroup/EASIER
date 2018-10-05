#   0 - solution number,
#   1 - PA,
#   2 - PerfQ,
#   3 - Dist

import numpy as np
import myfunction
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

base_dir = '../data/'

# Near-Pareto frontier from a chromosome long 4
pareto_folder_l4 = base_dir + 'FTA/length_4/pareto/'
file_l4 = pareto_folder_l4 + 'P_128_E_720_X_0.8_M_0.2_solutions.csv'
pareto_l4 = [1155, 1211, 1240, 208, 484, 938]
data_l4 = np.loadtxt(open(file_l4, "rb"), delimiter=";")  # , skiprows=1)
min_pa_l4 = min(data_l4[:, 1])
max_pa_l4 = max(data_l4[:, 1])
min_perfq_l4 = min(data_l4[:, 2])
max_perfq_l4 = max(data_l4[:, 2])
min_dist_l4 = min(data_l4[:, 3])
max_dist_l4 = max(data_l4[:, 3])

# Near-Pareto frontier from a chromosome long 6
pareto_folder_l6 = base_dir + 'FTA/length_6/pareto/'
file_l6 = pareto_folder_l6 + 'P_128_E_720_X_0.8_M_0.2_solutions.csv'
pareto_l6 = [1100, 1174, 1254, 298, 457, 613, 935, 961]
data_l6 = np.loadtxt(open(file_l6, "rb"), delimiter=";")  # , skiprows=1)
min_pa_l6 = min(data_l6[:, 1])
max_pa_l6 = max(data_l6[:, 1])
min_perfq_l6 = min(data_l6[:, 2])
max_perfq_l6 = max(data_l6[:, 2])
min_dist_l6 = min(data_l6[:, 3])
max_dist_l6 = max(data_l6[:, 3])

# Near-Pareto frontier from a chromosome long 8
pareto_folder_l8 = base_dir + 'FTA/length_8/pareto/'
file_l8 = pareto_folder_l8 + 'P_128_E_720_X_0.8_M_0.2_solutions.csv'
pareto_l8 = [1056, 1113, 1163, 584]
data_l8 = np.loadtxt(open(file_l8, "rb"), delimiter=";")  # , skiprows=1)
min_pa_l8 = min(data_l8[:, 1])
max_pa_l8 = max(data_l8[:, 1])
min_perfq_l8 = min(data_l8[:, 2])
max_perfq_l8 = max(data_l8[:, 2])
min_dist_l8 = min(data_l8[:, 3])
max_dist_l8 = max(data_l8[:, 3])

# Near-Pareto frontier from a chromosome long 8
pareto_folder_l10 = base_dir + 'FTA/length_10/pareto/'
file_l10 = pareto_folder_l10 + 'P_128_E_720_X_0.8_M_0.2_solutions.csv'
pareto_l10 = [1054, 1128, 1156, 1170, 1179, 222, 419, 721]
data_l10 = np.loadtxt(open(file_l10, "rb"), delimiter=";")  # , skiprows=1)
min_pa_l10 = min(data_l10[:, 1])
max_pa_l10 = max(data_l10[:, 1])
min_perfq_l10 = min(data_l10[:, 2])
max_perfq_l10 = max(data_l10[:, 2])
min_dist_l10 = min(data_l10[:, 3])
max_dist_l10 = max(data_l10[:, 3])

# normalize PA with respect to PerfQ
data_l4[:, 1] = myfunction.norm2perfq(min_perfq_l4, max_perfq_l4, data_l4[:, 1], min_pa_l4, max_pa_l4)
data_l6[:, 1] = myfunction.norm2perfq(min_perfq_l6, max_perfq_l6, data_l6[:, 1], min_pa_l6, max_pa_l6)
data_l8[:, 1] = myfunction.norm2perfq(min_perfq_l8, max_perfq_l8, data_l8[:, 1], min_pa_l8, max_pa_l8)
data_l10[:, 1] = myfunction.norm2perfq(min_perfq_l10, max_perfq_l10, data_l10[:, 1], min_pa_l10, max_pa_l10)

# normalize Dist with respect to PerfQ
data_l4[:,3] = myfunction.norm2perfq(min_perfq_l4, max_perfq_l4, data_l4[:,3], min_dist_l4, max_dist_l4)
data_l6[:,3] = myfunction.norm2perfq(min_perfq_l6, max_perfq_l6, data_l6[:,3], min_dist_l6, max_dist_l6)
data_l8[:,3] = myfunction.norm2perfq(min_perfq_l8, max_perfq_l8, data_l8[:,3], min_dist_l8, max_dist_l8)
data_l10[:,3] = myfunction.norm2perfq(min_perfq_l10, max_perfq_l10, data_l10[:,3], min_dist_l10, max_dist_l10)

# scale values
for i in range(1, 3):
    # mean-center data
    data_l4[:, i] -= data_l4[:, i].mean()
    data_l6[:, i] -= data_l6[:, i].mean()
    data_l8[:, i] -= data_l8[:, i].mean()
    data_l10[:, i] -= data_l10[:, i].mean()

    # divide by the standard deviation
    data_l4[:, i] /= data_l4[:, i].std()
    data_l6[:, i] /= data_l6[:, i].std()
    data_l8[:, i] /= data_l8[:, i].std()
    data_l10[:, i] /= data_l10[:, i].std()


# compute distances from pareto solutions

# plot the number of solution as delta increases
# sol_by_delta = [len({k:v for k,v in distances.items() if v <= delta}) for delta in np.arange(0.0, max(distances), 0.01)]
# max_by_delta = sol_by_delta.index(max(sol_by_delta))
# fig = plt.figure(figsize=(20, 15))
# plt.plot(np.arange(0.0, max_by_delta*0.01, 0.01), sol_by_delta[:max_by_delta])
# plt.grid(True)
# plt.show()

# remove solutions more distant than delta
# delta = max(distances)
delta = 0.038


fig = plt.figure(figsize=(15, 10))
fig.suptitle('easier_lengths', fontsize=16)
ax = fig.add_subplot(111, projection='3d')
ax.view_init(25, 15)
ax.set_xlabel('PA')
ax.set_ylabel('PerfQ')
ax.set_zlabel('Dist')

# pareto solutions and close solutions (delta)
myfunction.plot_pareto(ax, data_l4, pareto_l4, 's', '#000000', 'pareto_plot_l4')
myfunction.plot_pareto(ax, data_l6, pareto_l6, '^', '#000099', 'pareto_plot_l6')
myfunction.plot_pareto(ax, data_l8, pareto_l8, 'o', '#66FF11', 'pareto_plot_l8')
myfunction.plot_pareto(ax, data_l10, pareto_l10, 'x', 'r', 'pareto_plot_l10')


# myfunction.scatter_near_solution(ax, data_l4, pareto_l4, 's', '#000000', delta)
# myfunction.scatter_near_solution(ax, data_l6, pareto_l6, '^', '#000099', delta)
# myfunction.scatter_near_solution(ax, data_l8, pareto_l8, 'o', '#66FF11', delta)
# myfunction.scatter_near_solution(ax, data_l10, pareto_l10, 'x', 'r', delta)


ax.legend()
plt.show()
