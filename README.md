# About

The project will compare two images with the same size for similarities and it will return 0 in case if the images are same and 1 when the images have no similarity and everything else between 1 and 0.

## Building

To clone and build this project, run the following commands:

```bash
git clone https://github.com/amrinder09/Image-Comparision.git
```
Then open in the project in a IDE and build the project specific to that IDE.

## Usage

In the app.propperties file in the resource folder mention the following things:

Input_File_Path - This is the path of the input csv file containing the file path and image name of the images to be compared.
Output_File_Path - This is the file path and the file name where you want your output file to be created.

```Java
Windows OS format:

Input_File_Path=C:\TEST\Input.csv
Output_File_Path=C:\TEST\Output.csv

Mac/Linux OS format:

Input_File_Path=/Users/aircheema/Downloads/imgComparision/TEST/Sample.csv
Output_File_Path=/Users/aircheema/Downloads/imgComparision/TEST/Test.csv
```



Make sure that the file path contains the file name as well.
Run the program after that and a csv will be written in the output file path with the image similarities data inside it.

Make sure that the input file contains the data in the following format:

```Csv
Windows OS format:

Image1,Image2
C:\TEST\Images\Chrysanthemum.jpg,C:\TEST\Images\Desert.jpg
C:\TEST\Images\Desert.jpg,C:\TEST\Images\Desert.jpg
C:\TEST\Images\Desert.jpg,C:\TEST\Images\TEST1.jpg
C:\TEST\Images\Capture.jpg,C:\TEST\Images\Capture2.jpg

Mac/Linux OS format:

/Users/aircheema/Downloads/imgComparision/TEST/Images/Capture.JPG,/Users/aircheema/Downloads/imgComparision/TEST/Images/Capture2.JPG
/Users/aircheema/Downloads/imgComparision/TEST/Images/Capture.JPG,/Users/aircheema/Downloads/imgComparision/TEST/Images/Desert.jpg
```

The first line in input file is always the header and will be ignored and the rest of the lines are the absolute path and name of the image files that are to be compared.
These paths should be accessible to our program also for it to work on it.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.