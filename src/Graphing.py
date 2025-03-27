import random # import random variables 
with open ("GenericsKB.txt", "r") as file: # open the file
	lines = file.readlines();
# print  to check if its really reading

x = 0
n = [5, 10, 50, 100, 500, 1000, 5000, 10000, 25000, 50000]

for i in n:
	subset= random.sample(lines, i) #selects i lines from thelist containing  all the lines
	x+=1
	filename  = f"subset_{i}.txt" # creating filename according to the number of lines it contains
	with open(filename , "w") as output_file: # open file in write mode to save the subset of data we select , writing  each line to the file
		output_file.writelines(subset)
	print(f"Saved {i} lines to {filename}")