import { expect, test } from "@playwright/test";

test.describe("Greeting ページのテスト", () => {
  test.describe("初期表示のテスト", () => {
    test("Greeting ページが表示されること", async ({ page }) => {
      // Act
      await page.goto("/examples/greeting");

      // Assert
      await expect(
        page.getByRole("heading", { level: 1, name: "API Greeting Demo" }),
      ).toBeVisible();
    });

    test("文字列「Hello, World!」が表示されること", async ({ page }) => {
      // Act
      await page.goto("/examples/greeting");

      // Assert
      await expect(page.getByText("Hello, World!").first()).toBeVisible();
    });

    test("文字列「Hello, KeM198!」が表示されること", async ({ page }) => {
      // Act
      await page.goto("/examples/greeting");

      // Assert
      await expect(page.getByText("Hello, KeM198!").first()).toBeVisible();
    });
  });
});
