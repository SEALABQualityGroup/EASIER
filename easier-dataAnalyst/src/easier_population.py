#easier_long

import numpy as np
import myfunction
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

base_dir = '../data/'

# Near-Pareto frontier
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

# Near-Pareto frontier
pareto_folder_128_E1280 = base_dir + 'FTA/128/lenght_4/pareto/P_128_E_1280_X_0.8_M_0.2__18.10.03.13.22.43__/'
file_128_E1280 = pareto_folder_128_E1280 + 'P_128_E_1280_X_0.8_M_0.2_solutions.csv'
pareto_128_E1280 = [114, 1209, 1797, 186, 1868, 2189, 357]
data_128_E1280 = np.loadtxt(open(file_128_E1280, "rb"), delimiter=";")  # , skiprows=1)
min_pa_128_E1280= min(data_128_E1280[:, 1])
max_pa_128_E1280 = max(data_128_E1280[:, 1])
min_perfq_128_E1280 = min(data_128_E1280[:, 2])
max_perfq_128_E1280 = max(data_128_E1280[:, 2])
min_dist_128_E1280 = min(data_128_E1280[:, 3])
max_dist_128_E1280 = max(data_128_E1280[:, 3])

# Near-Pareto frontier
pareto_folder_256_E1280 = base_dir + 'FTA/256/lenght_4/pareto/P_256_E_1280_X_0.8_M_0.2__18.10.03.15.19.03__/'
file_256_E1280 = pareto_folder_256_E1280 + 'P_256_E_1280_X_0.8_M_0.2_solutions.csv'
pareto_256_E1280 = [1210, 1376, 1468, 1545, 1685, 1749, 1998, 973]
data_256_E1280 = np.loadtxt(open(file_256_E1280, "rb"), delimiter=";")  # , skiprows=1)
min_pa_256_E1280= min(data_256_E1280[:, 1])
max_pa_256_E1280 = max(data_256_E1280[:, 1])
min_perfq_256_E1280 = min(data_256_E1280[:, 2])
max_perfq_256_E1280 = max(data_256_E1280[:, 2])
min_dist_256_E1280 = min(data_256_E1280[:, 3])
max_dist_256_E1280 = max(data_256_E1280[:, 3])

# Near-Pareto frontier
pareto_folder_512_E1280 = base_dir + 'FTA/512/lenght_4/pareto/P_512_E_1280_X_0.8_M_0.2__18.10.03.17.05.38__/'
file_512_E1280 = pareto_folder_512_E1280 + 'P_512_E_1280_X_0.8_M_0.2_solutions.csv'
pareto_512_E1280 = [1105, 1348, 1738, 2096, 930]
data_512_E1280 = np.loadtxt(open(file_512_E1280, "rb"), delimiter=";")  # , skiprows=1)
min_pa_512_E1280= min(data_256_E1280[:, 1])
max_pa_512_E1280 = max(data_256_E1280[:, 1])
min_perfq_512_E1280 = min(data_256_E1280[:, 2])
max_perfq_512_E1280 = max(data_256_E1280[:, 2])
min_dist_512_E1280 = min(data_256_E1280[:, 3])
max_dist_512_E1280 = max(data_256_E1280[:, 3])



# normalize PA with respect to PerfQ
#data_64_E1280[:, 1] = myfunction.norm2perfq(min_perfq_64_E1280, max_perfq_64_E1280, data_64_E1280[:, 1], min_pa_64_E1280, max_pa_64_E1280)


# normalize Dist with respect to PerfQ
#data_64_E1280[:,3] = myfunction.norm2perfq(min_perfq_64_E1280, max_perfq_64_E1280, data_64_E1280[:,3], min_dist_64_E1280, max_dist_64_E1280)


# scale values
for i in range(1, 3):
   # mean-center data
   data_64_E1280[:, i] -= data_64_E1280[:, i].mean()
   data_128_E1280[:, i] -= data_128_E1280[:, i].mean()
   data_256_E1280[:, i] -= data_256_E1280[:, i].mean()
   data_512_E1280[:, i] -= data_512_E1280[:, i].mean()

   # divide by the standard deviation
   data_64_E1280[:, i] /= data_64_E1280[:, i].std()
   data_128_E1280[:, i] /= data_128_E1280[:, i].std()
   data_256_E1280[:, i] /= data_256_E1280[:, i].std()
   data_512_E1280[:, i] /= data_512_E1280[:, i].std()


# delta = max(distances)
delta = 0.2

fig = plt.figure(figsize=(30, 20))
fig.suptitle('easier_popolation', fontsize=16)
ax = fig.add_subplot(111, projection='3d')
ax.view_init(25, 15)
ax.set_xlabel('PA')
ax.set_ylabel('PerfQ')
ax.set_zlabel('Dist')

# pareto solutions and close solutions (delta)
myfunction.plot_pareto(ax, data_64_E1280, pareto_64_E1280, 's', 'r', 'pareto_plot_64_E1280')
# myfunction.scatter_near_solution(ax, data_64_E1280, pareto_64_E1280, 's', '#000000', 'solutions_64_E1280', delta)

myfunction.plot_pareto(ax, data_128_E1280, pareto_128_E1280, 'o', 'green', 'pareto_plot_128_E1280')
# myfunction.scatter_near_solution(ax, data_128_E1280, pareto_128_E1280, 'o', 'b', 'solutions_128_E1280', delta)

myfunction.plot_pareto(ax, data_256_E1280, pareto_256_E1280, '^', 'b', 'pareto_plot_256_E1280')
# myfunction.scatter_near_solution(ax, data_256_E1280, pareto_256_E1280, '^', 'green', 'solutions_256_E1280', delta)

myfunction.plot_pareto(ax, data_512_E1280, pareto_512_E1280, '+', '#ff9933', 'pareto_plot_512_E1280')
# myfunction.scatter_near_solution(ax, data_512_E1280, pareto_512_E1280, '+', 'grey', 'solutions_512_E1280', delta)
ax.legend()
plt.show()
