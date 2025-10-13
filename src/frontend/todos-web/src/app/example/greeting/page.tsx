import {
  GreetingCard,
  GreetingCardContent,
} from "@/components/example/greeting/greeting";

export default function GreetingPage() {
  return (
    <div className="flex flex-col gap-8 p-8">
      <h1 className="text-2xl font-bold text-slate-600">API Greeting Demo</h1>

      <GreetingCard
        title="Default Greeting"
        description="API: GET /v1/greeting/hello"
      >
        <GreetingCardContent />
      </GreetingCard>

      <GreetingCard
        title="Personalized Greeting"
        description="API: GET /v1/greeting/hello?name=KeM198"
      >
        <GreetingCardContent name="KeM198" />
      </GreetingCard>
    </div>
  );
}
