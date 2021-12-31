package Test;

import Test.Product;
import java.util.Scanner;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

public class Machine {
	
	private ArrayList<Product> ListProduct;
	private ArrayList<Integer> LogProduct = new ArrayList<>();
	private int CostLimit = 50000;
	static Scanner scanner = new Scanner(System.in);
	
	public Machine() {
		ListProduct = new ArrayList<>();
		ListProduct.add(new Product(1, "Coke", 10000, 100));
		ListProduct.add(new Product(2, "Pepsi", 10000, 100));
		ListProduct.add(new Product(3, "Soda", 20000, 100));
	}
	public void Output()
	{
		System.out.println("Cac san pham con lai: ");
        for (Product prod : ListProduct) {
            System.out.println(prod.toString());
        }
	}
	public Boolean CheckMoney(int Money)
	{
		if ((Money == 10000)||(Money == 20000)||(Money == 50000)||(Money == 100000)||(Money == 200000))
			return true;
		else return false;
	}
	public boolean Bingo()
	{
		Random random = new Random();
		int number = random.nextInt(10) + 1;
		if (number == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void Promotion(int product)
	{
		LogProduct.add(product);
		int length = LogProduct.size();
		
		if(length >=3 )
		{
			if ((LogProduct.get(length-1) == LogProduct.get(length-2)) && (LogProduct.get(length-1) == LogProduct.get(length-3)))
			{
				if (Bingo()==true)
				{
					Random random = new Random();
					int productRandom = random.nextInt(3) + 1;
					Product productPromotion = ListProduct.get(productRandom);
					int productPromotionCost = productPromotion.getCost();
					int productPromotionQuantity = productPromotion.getQuantity();
					
					if (CostLimit > productPromotionCost)
					{
						System.out.println("Chuc mung ban nhan them mot san pham" + productPromotion.getName());
						CostLimit -= productPromotionCost;
						productPromotion.setQuantity(productPromotionQuantity-1);
					}
					else
					{
						System.out.println("Chuong trinh da dat gioi han!");
					}
				}
				else
				{
					System.out.println("Chuc ban may man lan sau!");
				}
			}
		}
	}
	
	public int productChoose()
	{
		int product;
		System.out.println("Chon san phan can mua:\n1.Coke\n2.Pepsi\n3.Soda");
		product = scanner.nextInt();
		while ((product<1) && (product>3))
		{
			System.out.println("San pham khong hop le!");
			product = scanner.nextInt();
		}
		return product;
	}
	
	public Boolean Comfirm()
	{
		System.out.println("Xac nhan mua hang:\n1.Xac nhan\n2.Huy ");
		int confirm = scanner.nextInt();
		if (confirm == 1)
		{	
			return true;
		}
		else
		{
			return false;
		}
	}
	public void Buy() 
	{
		Output();
		int Money;
		System.out.println("Nhap so tien khach nhap vao: ");
		Money = scanner.nextInt();
		while (CheckMoney(Money) == false)
		{
			System.out.println("So tien khong hop le!");
			Money = scanner.nextInt();
		}
		
		int product = productChoose();		
		int productCost = ListProduct.get(product-1).getCost();
		
		if (Money < productCost)
		{
			System.out.println("So tien khong du!");
		}
		else
		{
			Money = Money - productCost;
			int temp = ListProduct.get(product-1).getQuantity();

			if (temp == 0)
			{
				System.out.println("Het san pham!");
			}
			else
			{
				Boolean confirm = Comfirm();
				if (confirm==true)
				{
					ListProduct.get(product-1).setQuantity(temp-1);
					System.out.println("Mua hang thanh cong!");
					System.out.println("So tien tra lai: " + Money);
					
					// Chuong trinh khuyen mai
					Promotion(product);
				}
				else
				{
					System.out.println("Hoan lai tien!");
				}
			}
		}
	}
	public void CheckPromotion()
	{
		if (CostLimit > 0)
		{
			System.out.println("Ti le thang cua ban se tang len 50% vao ngay hom sau");
		}
		else
		{
			System.out.println("Chuong trinh da dat gioi han!");
		}
		Output();
	}
	
	
	public static void main(String[] args) {
		Machine machine = new Machine();
		int choice;
	
		do 
		{
			System.out.println("Nhap lua chon:\n1.Tiep tuc mua hang\n2.Thoat");
			choice = scanner.nextInt();
			if (choice==1) 
			{
				machine.Buy();
			}
		}		
		while (choice == 1);
		machine.CheckPromotion();

	}
}
