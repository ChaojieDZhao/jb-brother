## mse：mean squared error，均方误差
## target：目标取值
## prediction：预测取值
## feature scaling：特征缩放
特征缩放是用来标准化数据特征的范围，适用于梯度下降算法，用来更好的收敛。既把一些大的特征值转成小一点，比如0到1的范围内

## dependent variable and independent variable：因变量和自变量
y=f(x)。此式表示为：y随x的变化而变化。y是因变量，x是自变量

## logarithms：对数
如果a的x次方等于n（a>0，且a不等于1），那么数x叫做以a为底n的对数（logarithm），记作x=logan。其中，a叫做对数的底数

## feature discretization：数据离散化
数据离散化是指将连续的数据进行分段，使其变为一段段离散化的区间。分段的原则有基于等距离、等频率或优化的方法。比如工资收入，月薪2000和月薪20000，从连续型特征来看高低薪的差异还要通过数值层面才能理解，但将其转换为离散型数据（底薪、高薪），则可以更加直观的表达出了我们心中所想的高薪和底薪。

## accuracy,precision,recall：精确率，精确率，召回率
假设我们手上有60个正样本，40个负样本，我们要找出所有的正样本，系统查找出50个，其中只有40个是真正的正样本，计算上述各指标。
TP: 将正类预测为正类数  40
FN: 将正类预测为负类数  20
FP: 将负类预测为正类数  10
TN: 将负类预测为负类数  30
准确率(accuracy) = 预测对的/所有 = (TP+TN)/(TP+FN+FP+TN) = 70%
精确率(precision) = TP/(TP+FP) = 80%
召回率(recall) = TP/(TP+FN) = 2/3

## SVM：Support Vector Machine，支持向量机

## dot product; scalar product：点积
a·b=（a^T）*b，这里的a^T指示矩阵a的转置。返回一个实数值标量。

## cross product：向量积
数学中又称外积、叉积，物理中称矢积、叉乘，是一种在向量空间中向量的二元运算。与点积不同，它的运算结果是一个向量而不是一个标量。
u=Xu*i+Yu*j+Zu*k；
v=Xv*i+Yv*j+Zv*k；
u x v=（Yu*Zv–Zu*Yv）*i+（Zu*Xv–Xu*Zv）*j+（Xu*Yv–Yu*Xv）*k