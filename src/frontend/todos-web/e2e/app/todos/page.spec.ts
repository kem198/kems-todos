import { expect, test } from "@playwright/test";

test.describe("Todos ページのテスト", () => {
  test.describe("初期表示のテスト", () => {
    test("追加済みのタスク「Added Task 1」が表示されること", async ({
      page,
    }) => {
      // Arrange
      // DB へ初期データを投入しておく
      const apiBaseUrl = process.env.API_BASE_URL;
      const res = await page.request.post(`${apiBaseUrl}/v1/todos`, {
        headers: { "Content-Type": "application/json" },
        data: {
          todoTitle: "Added Task 1",
          todoDescription: "Added Task 1 description",
        },
      });
      expect(res.ok()).toBeTruthy();
      const { todoId } = (await res.json()) as { todoId?: string };
      // テスト対象画面の前ページへ遷移しておく
      await page.goto("/");

      // Act
      await page.getByRole("button", { name: "/todos" }).click();

      // Assert
      await expect(page.getByText("Added Task 1").first()).toBeVisible();

      // Cleanup
      if (todoId) {
        await page.request
          .delete(`${apiBaseUrl}/v1/todos/${todoId}`)
          .catch(() => {});
      }
    });
  });

  test.describe("タスク作成時のテスト", () => {
    test("入力フォームからタスクを追加した結果、追加済みのタスク「Added Task 2」が表示されること", async ({
      page,
    }) => {
      // Arrange
      await page.goto("/todos");
      await page.getByRole("textbox", { name: "Type todo title..." }).click();
      await page
        .getByRole("textbox", { name: "Type todo title..." })
        .fill("Added Task 2");

      // Act
      await page.getByRole("button", { name: "Add" }).click();

      // Assert
      await expect(page.getByText("Added Task 2").first()).toBeVisible();

      // Cleanup
      // TODO: 作成する
    });
  });
});
