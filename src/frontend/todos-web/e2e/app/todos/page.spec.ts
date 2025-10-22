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

      // Capture screenshot before action
      await page.screenshot({
        path: "screenshots/todos/create-test/added-task-2-before.png",
        fullPage: true,
      });

      // Act
      // TODO: 「タイトル」フォームへタスク名を入力する
      // TODO: 「説明」フォームへタスクの説明を入力する
      // TODO: 「追加」ボタンをクリックする

      // Assert
      await expect(page.getByText("Added Task 2").first()).toBeVisible();
    });
  });
});
