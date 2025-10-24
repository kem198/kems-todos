import { getTodos } from "@/features/todos/utils/actions";
import { beforeEach, describe, expect, it, MockInstance, vi } from "vitest";

describe("getTodos() のテスト", () => {
  process.env.API_BASE_URL = "http://localhost:8080";
  let fetchMock: MockInstance;
  const expectedResponse = [
    {
      todoTitle: "タスクのタイトルタスクのタイトル",
      todoDescription:
        "タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明",
      finished: false,
    },
    {
      todoTitle: "タスクのタイトルタスクのタイトル",
      todoDescription:
        "タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明タスクの説明",
      finished: false,
    },
  ];

  beforeEach(() => {
    fetchMock = vi.spyOn(globalThis, "fetch");
    fetchMock.mockImplementation(
      async () =>
        new Response(JSON.stringify(expectedResponse), { status: 200 }),
    );
  });

  it("Todo[] を返すこと", async () => {
    // Arrange

    // Act
    const [todos] = await getTodos();

    // Assert
    expect(todos).toEqual(expectedResponse);
  });

  it("Response を返すこと", async () => {
    // Arrange

    // Act
    const [todos, response] = await getTodos();

    // Assert
    // TODO: もっとまともにする？
    expect(response).toBeDefined();
  });

  it("レスポンスが OK でなかったら例外をスローすること", async () => {
    // Arrange
    fetchMock.mockImplementationOnce(
      async () =>
        new Response('{"error":"server"}', {
          status: 500,
          statusText: "Internal Server Error",
        }),
    );

    // Act & Assert
    await expect(getTodos()).rejects.toThrow();
  });
});
