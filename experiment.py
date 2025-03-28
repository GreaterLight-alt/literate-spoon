import pandas as pd

def parse_search_count_file(filename):
    """
    Parse the searchCount file with the provided structure.
    """
    try:
        with open(filename, 'r') as file:
            lines = file.readlines()

        # Initialize variables to store parsed data
        results = []
        sample_size = None
        comparisons_data = []
        total_comparisons = 0
        best_case = None
        worst_case = None
        average_case = None

        for line in lines:
            line = line.strip()

            # Detect and process sample size lines
            if line.startswith("Number of comparisons done when searching a sample of size n:"):
                if sample_size is not None:
                    # Add previous sample's data to results
                    results.append({
                        'Sample Size': sample_size,
                        'Total Comparisons': total_comparisons,
                        'Best Case': best_case,
                        'Worst Case': worst_case,
                        'Average Case': average_case,
                        'Comparisons Data': comparisons_data
                    })
                
                # Reset variables for the new sample size
                sample_size = int(line.split(":")[1].strip())
                comparisons_data = []
                total_comparisons = 0
                best_case = None
                worst_case = None
                average_case = None

            # Process individual comparisons for this sample
            elif line and line[0].isdigit():
                parts = line.split()
                if len(parts) == 2:
                    try:
                        comparisons_data.append(int(parts[1]))
                        total_comparisons += int(parts[1])
                    except ValueError:
                        print(f"Skipping invalid line: {line}")

            # Process total comparisons line
            elif line.startswith("Total search comparisons"):
                total_comparisons = int(line.split()[-1])

            # Process best case line
            elif line.startswith("The best case"):
                best_case = int(line.split()[-1])

            # Process worst case line
            elif line.startswith("The worst case"):
                worst_case = int(line.split()[-1])

            # Process average case line
            elif line.startswith("The average case"):
                average_case = float(line.split()[-1])

        # Add the last sample's data
        if sample_size is not None:
            results.append({
                'Sample Size': sample_size,
                'Total Comparisons': total_comparisons,
                'Best Case': best_case,
                'Worst Case': worst_case,
                'Average Case': average_case,
                'Comparisons Data': comparisons_data
            })

        # Convert to DataFrame
        df = pd.DataFrame(results)

        # Return the DataFrame
        return df

    except Exception as e:
        print(f"Error parsing {filename}: {e}")
        return pd.DataFrame()


def main():
    # Parse the searchCount file
    try:
        search_df = parse_search_count_file('searchCount.txt')
        
        # Check if the dataframe is empty
        if search_df.empty:
            print("No valid data to write to Excel.")
            return
        
        # Create an Excel file with the results
        try:
            with pd.ExcelWriter('Search_Comparison_Analysis.xlsx') as writer:
                search_df.to_excel(writer, sheet_name='Search Comparisons', index=False)
            print("Data successfully written to 'Search_Comparison_Analysis.xlsx'")
        except Exception as e:
            print(f"Error writing to Excel: {e}")
            return

    except Exception as e:
        print("An unexpected error occurred:")
        print(e)
        return

if __name__ == "__main__":
    main()







