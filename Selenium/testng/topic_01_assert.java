package testng;

import org.testng.Assert;

public class topic_01_assert {

	public static void main(String[] args) {

		String fullName = " Mai Thị Cẩm Tiên";
		
		// mong đợi 1 điều kiện trả về đúng
		Assert.assertTrue(fullName.contains("Tiên"));
		
		// mong đợi 1 điều kiện trả về SAI
		Assert.assertFalse(fullName.contains("hoa"));
		

		// mong đợi 2 điều kiện bằng nhau
		Assert.assertEquals(fullName, " Mai Thị Cẩm Tiên");
	}

}
