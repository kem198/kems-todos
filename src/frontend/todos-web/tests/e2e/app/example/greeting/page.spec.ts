import { expect, test } from "@playwright/test";

test("Greeting ページが表示されること", async ({ page }) => {
  // Act
  await page.goto("/example/greeting");

  // Assert
  await expect(
    page.getByRole("heading", { level: 1, name: "API Greeting Demo" }),
  ).toBeVisible();
});
