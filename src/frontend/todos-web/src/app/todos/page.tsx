import { AppPage } from "@/components/layout/app-page";
import { TypographyH1 } from "@/components/typography/typography";
import { TodosClient } from "@/features/todos/components/todos-client";
import { getTodos } from "@/features/todos/utils/actions";

export default async function TodosPage() {
  const [todos, responseData] = await getTodos();

  return (
    <AppPage className="mx-auto max-w-3xl">
      <TypographyH1>Todos</TypographyH1>
      <TodosClient initialTodos={todos} responseData={responseData} />
    </AppPage>
  );
}
