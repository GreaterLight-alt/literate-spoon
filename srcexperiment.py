import pandas as pd

def parse_comparisons(file_path):
    comparisons = []
    with open(file_path, 'r') as file:
        lines = file.readlines()

        # Parse data for both insert and search comparisons
        for line in lines:
            if line.strip() and not line.startswith("Number of comparisons done"):
                try:
                    # Extracting the index and comparison count
                    parts = line.strip().split()
                    index = int(parts[0])
                    comparison_count = int(parts[1])
                    comparisons.append((index, comparison_count))
                except ValueError:
                    # Skip any lines that do not fit the expected format
                    pass
    return comparisons

def parse_summary(file_path):
    # This function assumes that the summary follows the format:
    # Total insert comparisons <value>
    # The best case: <value>
    # The worst case: <value>
    # The average case: <value>
    summary = {}
    with open(file_path, 'r') as file:
        lines = file.readlines()
        
        for line in lines:
            if line.startswith("Total"):
                summary["Total"] = int(line.split(":")[1].strip())
            elif line.startswith("The best case"):
                summary["Best case"] = int(line.split(":")[1].strip())
            elif line.startswith("The worst case"):
                summary["Worst case"] = int(line.split(":")[1].strip())
            elif line.startswith("The average case"):
                summary["Average case"] = float(line.split(":")[1].strip())
    
    return summary

def process_experiment_data(insert_file, search_file):
    # Parse the insert data
    insert_comparisons = parse_comparisons(insert_file)
    insert_summary = parse_summary(insert_file)

    # Parse the search data
    search_comparisons = parse_comparisons(search_file)
    search_summary = parse_summary(search_file)

    # Combine both insert and search data into a DataFrame for easy comparison
    insert_df = pd.DataFrame(insert_comparisons, columns=["Index", "Insert Comparisons"])
    search_df = pd.DataFrame(search_comparisons, columns=["Index", "Search Comparisons"])

    # Merge dataframes on Index to have a side-by-side comparison
    result_df = pd.merge(insert_df, search_df, on="Index", how="outer")

    # Print results
    print("Insert Summary:")
    for key, value in insert_summary.items():
        print(f"{key}: {value}")

    print("\nSearch Summary:")
    for key, value in search_summary.items():
        print(f"{key}: {value}")

    print("\nCombined Data (Insert vs Search Comparisons):")
    print(result_df)

# Define file paths
insert_file = "insertCount.txt"
search_file = "searchCount.txt"

# Process the data and display results
process_experiment_data(insert_file, search_file)
