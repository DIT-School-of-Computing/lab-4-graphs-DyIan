package ie.tudublin;

import java.time.Month;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	int mode = 1;
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
		float w = (width - 100) / (float)months.length;
		int Max_Ind = FindMax();
		float Max_Y = rainfall[Max_Ind];
		int Bottom = height - 50;
		int Colour = 0;

		// Calculate The Step For Y Axis
		float offset = ceil(Max_Y / (float)Num_Axis_Values);
		float h = 450 / (Num_Axis_Values + 1);	// This Is Because 0 Is Included On The Axis

		// Space = 3x, 12x = Months  500 / 15 = 33.33
		switch (mode)
		{
			case 0:
			{
				background(0);

				// Graph Title
				textAlign(CENTER);
				text("Rainfall Bar Chart", 250, 25);

	
				for(int i = 0 ; i < months.length ;  i ++)
				{
					// Map X Values
					float x = map1(i, 0, months.length, 50, Bottom);
					float y = map1(rainfall[i], 0, Max_Y, 50, Bottom);
			
					fill(Colour, 255, 255);
					strokeWeight(3);
					stroke(255, 0, 255);
					rect(x, Bottom, w, -y + 50);
					Colour += (20);
				
					textAlign(LEFT);
					text(months[i], x, 460);
		
				}
	
			
				stroke(255, 0, 255);
				//fill(0, 0, 255);
				// Y Axis - 50 is 1.5 x 33.33
				line(50, 50, 50, 450);
				// X Axis
				line(50, 450, 450, 450);
		

				
				for (int i = 0; i <= Num_Axis_Values; i++)
				{
					// Axis Numbers
					fill(255, 0, 255);
					text((int)offset * i , 20, Bottom - (i * h));	
					line(50, Bottom - (i * h), 40, Bottom - (i * h));
				}
				break;
			}

			case 1:
			{
				background(0);
				
				textAlign(CENTER);
				text("Rainfall Trend Chart", 250, 25);


				stroke(255, 0, 255);
				line(50, 50, 50, 450);
				// X Axis
				line(50, 450, 450, 450);

				// Have To Manually Print Jan, Since We Start At Index 1
				textAlign(LEFT);
				text(months[0], 50, 460);
				for (int i = 1; i < months.length; i++)
				{
					float x1 = map1(i - 1, 0, months.length, 50, Bottom);
					float x2 = map1(i, 0, months.length, 50, Bottom);
					float y1 = map1(rainfall[i - 1], 0, Max_Y, 50, Bottom);
					float y2 = map1(rainfall[i], 0, Max_Y, 50, Bottom);

					// Needs To Be Inverted
					line(x1,(height - y1), x2,(height - y2));

					// Prints Months On X-Axis
					text(months[i], x2, 460);

				}
				

				// Axis Stuff
				for (int i = 0; i <= Num_Axis_Values; i++)
				{
					// Axis Numbers
					fill(255, 0, 255);
					text((int)offset * i , 20, Bottom - (i * h));	
					line(50, Bottom - (i * h), 40, Bottom - (i * h));
				}
		
				break;
			} // End Case 1
			
			default:
				break;
		} // End Switch
			//noLoop();	
	}// End Draw
} // End Class
