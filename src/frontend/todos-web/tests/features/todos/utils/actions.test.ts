import { getTodos } from "@/features/todos/utils/actions";
import { beforeEach, describe, expect, it, MockInstance, vi } from "vitest";

describe("getTodos() のテスト", () => {
  process.env.API_BASE_URL = "http://localhost:8080";
  let fetchMock: MockInstance;

  beforeEach(() => {
    fetchMock = vi.spyOn(globalThis, "fetch");
    fetchMock.mockImplementation(
      async () => new Response('{ "key": "value" }', { status: 200 }),
    );
  });

  it("Todo[] を返すこと", async () => {
    // Arrange

    // Act
    const [todos] = await getTodos();

    // Assert
    expect(todos).toBeDefined();
  });

  it("Response を返すこと", async () => {
    // Arrange

    // Act
    const [todos, response] = await getTodos();

    // Assert
    expect(response).toBeDefined();
  });

  it.skip("○○の時○○エラーをスローすること", async () => {});
});
