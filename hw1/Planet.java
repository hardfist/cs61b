
public class Planet
{
    public Planet(double x,double y,double xv,double yv,double mass,String image)
    {
        this.x = x;
        this.y = y;
        this.xVelocity = xv;
        this.yVelocity = yv;
        this.mass = mass;
        this.img = image;
    }
    public double calcDistance(Planet rhs)
    {
        double dx = rhs.x - x;
        double dy = rhs.y - y;
        return Math.sqrt(dx*dx+dy*dy);
    }
    public double calcPairwiseForce(Planet rhs)
    {
        return G * mass * rhs.mass /(calcDistance(rhs)*calcDistance(rhs));
    }
    public double xCos(Planet rhs)
    {
        double dx = rhs.x - x;
        return dx/calcDistance(rhs);
    }
    public double yCos(Planet rhs)
    {
        double dy = rhs.y - y;
        return dy/calcDistance(rhs);
    }
    public double calcPairwiseForceX(Planet rhs)
    {
        return calcPairwiseForce(rhs)*xCos(rhs);
    }
    public double calcPairwiseForceY(Planet rhs)
    {
        return calcPairwiseForce(rhs)*yCos(rhs);
    }
    public void setNetForce(Planet[] planets)
    {
        double totalX = 0.0;
        double totalY = 0.0;
        for(Planet planet : planets)
        {
            if(planet != this)
            {
                totalX += calcPairwiseForce(planet)*xCos(planet);
                totalY += calcPairwiseForce(planet)*yCos(planet);
            }
        }
        xNetForce = totalX;
        yNetForce = totalY;
    }
    void Draw()
    {
      StdDraw.picture(x, y, "images/"+img);
    }
    void update(double delta)
    {
        xAccel = xNetForce/mass;
        yAccel = yNetForce/mass;
        xVelocity += xAccel*delta;
        yVelocity += yAccel*delta;
        x += xVelocity*delta;
        y += yVelocity*delta;  
    }
    public String toString()
    {
        return x+" "+y + " " + xVelocity + " " + yVelocity + " " + mass + " " + img;
    }
    public double x;
    public double y;
    public double xVelocity;
    public double yVelocity;
    public double mass;
    public double xNetForce;
    public double yNetForce;
    public double xAccel;
    public double yAccel;
    public String img;
    public final double G = 6.67E-11;
}
