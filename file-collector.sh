#!/bin/bash

# Check if the correct number of arguments is provided
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <input_directory> <output_file>"
    exit 1
fi

input_dir="$1"
output_file="$2"

# Check if input directory exists
if [ ! -d "$input_dir" ]; then
    echo "Error: Input directory '$input_dir' does not exist."
    exit 1
fi

# Clear the output file if it exists, or create it
> "$output_file"

# Find all files recursively and process them
find "$input_dir" -type f | while read -r file; do
    # Print separator with filename
    echo "=== $file ===" >> "$output_file"
    # Append file content to output
    cat "$file" >> "$output_file"
    # Add a newline for separation
    echo "" >> "$output_file"
done

echo "All files have been combined into '$output_file'."
