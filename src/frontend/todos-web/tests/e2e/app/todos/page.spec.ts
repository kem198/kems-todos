import { expect, test } from "@playwright/test";

test.describe("Todos ページのテスト", () => {
  test.describe("初期表示のテスト", () => {
    test("追加済みのタスク「Added Task 1」が表示されること", async ({
      page,
    }) => {
      // TODO: API 通信であらかじめ "Added Task 1" を挿入しておく

      // Act
      await page.goto("/todos");

      // Assert
      await expect(page.getByText("Added Task 1").first()).toBeVisible();

      // Capture screenshot after action
      await page.screenshot({
        path: "screenshots/todos/initial-test/added-task-1-after.png",
        fullPage: true,
      });
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

      // Capture screenshot after action
      await page.screenshot({
        path: "screenshots/todos/create-test/added-task-2-after.png",
        fullPage: true,
      });
    });
  });
});
