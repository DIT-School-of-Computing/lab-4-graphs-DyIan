package ie.tudublin;

import java.time.Month;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	int mode = 0;
	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};

	// For Bar Chart
	int Num_Axis_Values = 12;
	int maxIndex = 0;

	public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		println(mode);
	}

	public float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	public int FindMax()
	{
	
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] > rainfall[maxIndex])
			{
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	void randomize()
	{
		for (int i = 0; i < rainfall.length; i++) {
			rainfall[i] = random(500);
		}
	}

	public void settings()
	{
		size(500, 500);

		String[] m1 = months;
		months[0] = "XXX";
		print(m1[0]);
		for(int i = 0; i < months.length; i ++)
		{
			println("Month: " + months[i] + "\t" + rainfall[i]);
		}
		for (String s : m1) {
			println(s);
		}

		int minIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] < rainfall[minIndex])
			{
				minIndex = i;
			}
		}
		

		println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
		println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");
		
		
		float tot = 0;
		for(float f:rainfall)
		{
			tot += f;
		}

		float avg = tot / (float) rainfall.length;

		// a b-c d-e;
		println(map1(5, 0, 10, 0, 100));
		// 50

		println(map1(25, 20, 30, 200, 300));
		// 250

		println(map1(26, 25, 35, 0, 100));
		// 10 

	}

	public void setup() {
		colorMode(HSB);
		background(0);
		randomize();
	}

	
	public void draw()
	{	
		// Space = 3x, 12x = Months  500 / 15 = 33.33
		switch (mode) {
			case 0:
			{
				background(0);

			// Graph Title
			text("Rainfall Bar Chart", 200, 25);
	
			// There Are 2 50px Spaces On Either Side.
			float w = (width - 100) / (float)months.length;
			float Max_Y = 0;
			int Max_Ind = FindMax();
			int Bottom = height - 50;
			int Colour = 0;
	
			for(int i = 0 ; i < months.length ;  i ++)
			{
				// Map X Values
				float x = map1(i, 0, months.length, 50, (width - 50));
				float y = map1(rainfall[i], 0, rainfall[Max_Ind], 50, height - 50);
		
				fill(Colour, 255, 255);
				strokeWeight(3);
				stroke(255, 0, 255);
				rect(x, Bottom, w, -y + 50);
				Colour += (20);
				
				if (rainfall[i] > Max_Y)
				{
					Max_Y = rainfall[i];
					print("Max Y Changed To :", Max_Y, "\n");
				}
	
				// Print Months On X Axis
				text(months[i], x, 460);
	
			}
	
			
			stroke(255, 0, 255);
			//fill(0, 0, 255);
			// Y Axis - 50 is 1.5 x 33.33
			line(50, 50, 50, 450);
			// X Axis
			line(50, 450, 450, 450);
	
			// Calculate How Much We Got To Offset Values By
			float offset = ceil(Max_Y / (float)Num_Axis_Values);
			float h = 450 / (Num_Axis_Values + 1);	// This Is Because 0 Is Included On The Axis
	
			for (int i = 0; i <= Num_Axis_Values; i++)
			{
				// Axis Numbers
				fill(255, 0, 255);
				text((int)offset * i , 20, (height - 50) - i * h);	
				line(50, (height - 50) - i * h, 40, (height - 50) - i * h);
			}
				break;
			}

			case 1:
			{
				background(0);
				break;
			}
			

			default:
				break;
		}
		
		
		noLoop();	
	}
}
